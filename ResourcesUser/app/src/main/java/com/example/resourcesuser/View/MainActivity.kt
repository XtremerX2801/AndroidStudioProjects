package com.example.resourcesuser.View

import android.app.SearchManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.resourcesuser.Adapter.ArticlesAdapter
import com.example.resourcesuser.Api.NewsApi
import com.example.resourcesuser.Api.NewsApiService
import com.example.resourcesuser.Fragment.FilterFragment
import com.example.resourcesuser.Model.Doc
import com.example.resourcesuser.R
import com.example.resourcesuser.Repository.ArticleRepository
import com.example.resourcesuser.Utils.onItemClickListener
import com.example.resourcesuser.ViewModel.ArticleViewModel
import com.example.resourcesuser.ViewModel.ArticleViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private lateinit var mainAdapter: ArticlesAdapter
    private lateinit var avm : ArticleViewModel
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        avm = viewModel()

        val page = if (avm.getPage() == null) {
            0
        } else avm.getPage()!!

        if (avm.getQuery() != null) {
            if (avm.getBeginDate() != null) {
                if (avm.getSort() != null) {
                    if (avm.getNewsDesk() != null) {
                        avm.getSearchQueryBeginDateSortNewsDesk(
                            avm.getQuery()!!,
                            avm.getBeginDate()!!,
                            avm.getSort()!!,
                            avm.getNewsDesk()!!,
                            page
                        )
                    } else {
                        avm.getSearchQueryBeginDateSort(
                            avm.getQuery()!!,
                            avm.getBeginDate()!!,
                            avm.getSort()!!,
                            page
                        )
                    }
                } else if (avm.getNewsDesk() != null) {
                    avm.getSearchQueryBeginDateNewsDesk(
                        avm.getQuery()!!,
                        avm.getBeginDate()!!,
                        avm.getNewsDesk()!!,
                        page
                    )
                } else {
                    avm.getSearchQueryBeginDate(avm.getQuery()!!, avm.getBeginDate()!!,
                        page)
                }
            } else if (avm.getSort() != null) {
                if (avm.getNewsDesk() != null) {
                    avm.getSearchQuerySortNewsDesk(
                        avm.getQuery()!!,
                        avm.getSort()!!,
                        avm.getNewsDesk()!!,
                        page
                    )
                } else {
                    avm.getSearchQuerySort(avm.getQuery()!!, avm.getSort()!!,
                        page)
                }
            } else if (avm.getNewsDesk() != null) {
                avm.getSearchQueryNewsDesk(avm.getQuery()!!, avm.getNewsDesk()!!,
                    page)
            } else {
                avm.getSearchQuery(avm.getQuery()!!,
                    page)
            }
        } else {
            avm.getSearch(page)
        }
        Log.d("check", page.toString())

        mainAdapter = ArticlesAdapter()
        bindViewModel()
        
        val itemClickListener = object: onItemClickListener {
            override fun onItemClick(item: Doc) {
                val url = item.webUrl
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(this@MainActivity, Uri.parse(url))
            }
        }
        mainAdapter.setItemClickListener(itemClickListener)
        mainAdapter.notifyDataSetChanged()
        mainView.adapter = mainAdapter
        staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        mainView.layoutManager = staggeredGridLayoutManager

        //////////////////////////////  LOADMORE HERE, BUT LOW QUALITY
        addScrollerListener()
    }

    private fun bindViewModel(){
        avm.news.observe(this, Observer {
            mainAdapter.setArticle(it)
        })
    }

    private fun viewModel() : ArticleViewModel{
        val viewModelFactory = ArticleViewModelFactory(ArticleRepository(NewsApiService(NewsApi.create())))
        return ViewModelProvider(this, viewModelFactory)[ArticleViewModel::class.java]
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        searchBar(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mainFilter -> {
                val fm = supportFragmentManager
                val filterFragment = FilterFragment()
                filterFragment.show(fm, "fragment_edit_name")
                true

            }
            R.id.mainSetting -> {
                avm = viewModel()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun searchBar(menu: Menu?){
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                Log.d("checkDate", getBeginDate().toString())
                Log.d("checkSort", getSort())
                Log.d("checkNewsDesk", getNewsDesk())

                mainAdapter.resetAdapter()

                if (getBeginDate() != 0) {
                    if (getSort() != "") {
                        if (getNewsDesk() != "") {
                            avm.getSearchQueryBeginDateSortNewsDesk(
                                query,
                                getBeginDate(),
                                getSort(),
                                getNewsDesk(), 0
                            )
                        } else {
                            avm.getSearchQueryBeginDateSort(query, getBeginDate(), getSort(), 0)
                        }
                    } else if (getNewsDesk() != "") {
                        avm.getSearchQueryBeginDateNewsDesk(query, getBeginDate(), getNewsDesk(), 0)
                    } else {
                        avm.getSearchQueryBeginDate(query, getBeginDate(), 0)
                    }
                } else if (getSort() != "") {
                    if (getNewsDesk() != "") {
                        avm.getSearchQuerySortNewsDesk(query, getSort(), getNewsDesk(), 0)
                    } else {
                        avm.getSearchQuerySort(query, getSort(), 0)
                    }
                } else if (getNewsDesk() != "") {
                    avm.getSearchQueryNewsDesk(query, getNewsDesk(), 0)
                } else {
                    avm.getSearchQuery(query, 0)
                }

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    fun getBeginDate(): Int{
        val date = intent.getStringExtra("date")
        return if (date != ""){
            date?.toInt() ?: 0
        } else 0
    }

    fun getSort(): String{
        val sort = intent.getStringExtra("sort")
        return if (sort.isNullOrEmpty()){
            ""
        } else sort
    }

    fun getNewsDesk(): String{
        val newsDesk = intent.getStringExtra("news_desk")
        return if (newsDesk.isNullOrEmpty()){
            ""
        } else "news_desk:($newsDesk)"
    }

    private var mHandler: Handler? = null
    private var list: ArrayList<Int> = ArrayList()
    private var isLoading = false

    private fun addScrollerListener() {
        mainView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val page = avm.getPage()!! + 1

                Log.d("checkPage", page.toString())

                if (avm.getQuery() != null) {
                    if (avm.getBeginDate() != null) {
                        if (avm.getSort() != null) {
                            if (avm.getNewsDesk() != null) {
                                avm.getSearchQueryBeginDateSortNewsDesk(
                                    avm.getQuery()!!,
                                    avm.getBeginDate()!!,
                                    avm.getSort()!!,
                                    avm.getNewsDesk()!!,
                                    page
                                )
                            } else {
                                avm.getSearchQueryBeginDateSort(
                                    avm.getQuery()!!,
                                    avm.getBeginDate()!!,
                                    avm.getSort()!!,
                                    page
                                )
                            }
                        } else if (avm.getNewsDesk() != null) {
                            avm.getSearchQueryBeginDateNewsDesk(
                                avm.getQuery()!!,
                                avm.getBeginDate()!!,
                                avm.getNewsDesk()!!,
                                page
                            )
                        } else {
                            avm.getSearchQueryBeginDate(avm.getQuery()!!, avm.getBeginDate()!!,
                                page)
                        }
                    } else if (avm.getSort() != null) {
                        if (avm.getNewsDesk() != null) {
                            avm.getSearchQuerySortNewsDesk(
                                avm.getQuery()!!,
                                avm.getSort()!!,
                                avm.getNewsDesk()!!,
                                page
                            )
                        } else {
                            avm.getSearchQuerySort(avm.getQuery()!!, avm.getSort()!!,
                                page)
                        }
                    } else if (avm.getNewsDesk() != null) {
                        avm.getSearchQueryNewsDesk(avm.getQuery()!!, avm.getNewsDesk()!!,
                            page)
                    } else {
                        avm.getSearchQuery(avm.getQuery()!!,
                            page)
                    }
                } else {
                    avm.getSearch(page)
                }

                if (!isLoading) {
                    val lastVisibleItemPositions: IntArray =
                        staggeredGridLayoutManager.findLastVisibleItemPositions(null)
                    val lastVisibleItem: Int = getLastVisibleItem(lastVisibleItemPositions)
                    val totalItemCount: Int = staggeredGridLayoutManager.itemCount
                    if (!isLoading && totalItemCount <= lastVisibleItem + 1) {
                        loadMore()
                        isLoading = true
                    }
                }

            }
        })
    }

    fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    private fun loadMore() {
        mHandler?.post {
            mainAdapter.notifyItemInserted(list.size - 1)
        }
        mHandler?.postDelayed({
            mainAdapter.notifyDataSetChanged()
            isLoading = false
        }, 4000)
    }

}
