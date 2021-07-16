package meli.com.meliproducts.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.appcompat.widget.SearchView
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import meli.com.meliproducts.R
import meli.com.meliproducts.data.model.Product
import meli.com.meliproducts.data.model.Resource
import meli.com.meliproducts.databinding.FragmentProductListBinding
import meli.com.meliproducts.extensions.hideKeyboard
import meli.com.meliproducts.ui.main.adapter.ProductListAdapter

/**
 * Fragment for searching and displaying results in a list.
 */
class ProductListFragment : BaseFragment() {

    private lateinit var navController: NavController
    private lateinit var productListAdapter: ProductListAdapter

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by hiltNavGraphViewModels(
        R.id.nav_graph
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        productListAdapter = ProductListAdapter {
            val action = ProductListFragmentDirections.actionMainFragmentToDetailFragment(it.id)
            navController.navigate(action)
        }

        //Toolbar setup
        binding.toolbar.inflateMenu(R.menu.main_fragment)
        binding.toolbar.setTitle(R.string.app_name)
        val searchItem = binding.toolbar.menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.queryHint = requireContext().getString(R.string.search_hint)
        searchView.setOnQueryTextListener(getOnQueryTextListener())

        //RecyclerView setup
        binding.productsList.layoutManager = LinearLayoutManager(context)
        binding.productsList.adapter = productListAdapter
        binding.productsList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.productList.observe(viewLifecycleOwner, {
            handleFetchResponse(it)
        })

        showEmptySearch(true)
    }

    private fun fetchResults(query: String) {
        viewModel.getSearchResults(query)
    }

    private fun handleFetchResponse(productList: Resource<List<Product?>>) {
        when (productList) {
            is Resource.Loading -> {
                showEmptySearch(false)
                binding.loadingView.root.visibility = View.VISIBLE
            }
            is Resource.Success -> {
                showEmptySearch(false)
                productListAdapter.productList = productList.data
                binding.loadingView.root.visibility = View.GONE
            }
            else -> {
                showEmptySearch(true)
                binding.loadingView.root.visibility = View.GONE
                displayErrorMessage(
                    view = binding.loadingView.root,
                    message = requireContext().getString(R.string.error_fetch_failed),
                    duration = Snackbar.LENGTH_LONG
                )
            }
        }
    }

    private fun getOnQueryTextListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                query?.let { fetchResults(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.query.value = newText
                return true
            }
        }
    }

    override fun clearBindings() {
        _binding = null
    }

    private fun showEmptySearch(isShow: Boolean) {
        if (isShow) {
            // Set the content view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the animation.
            binding.emptyMessage.visibility = View.VISIBLE
            binding.animationView.alpha = 0f
            binding.animationView.visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            binding.animationView.animate().alpha(1f).duration = 600
            binding.animationView.setAnimation("json/search_empty.json")
            binding.animationView.repeatCount = Animation.INFINITE
            binding.animationView.playAnimation()
        } else {
            // Animate the loading view to 0% opacity. After the animation ends,
            // set its visibility to GONE as an optimization step (it won't
            // participate in layout passes, etc.)
            binding.emptyMessage.visibility = View.GONE
            binding.animationView.animate()
                .alpha(0f)
                .setDuration(600)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        binding.animationView.visibility = View.GONE
                    }
                })
        }
    }
}
