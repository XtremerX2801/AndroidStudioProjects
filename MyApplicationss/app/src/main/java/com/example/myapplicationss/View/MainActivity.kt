package com.example.myapplicationss.View

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplicationss.Api.NewsApi
import com.example.myapplicationss.Api.NewsApiService
import com.example.myapplicationss.ArticlesAdapter
import com.example.myapplicationss.FilterFragment
import com.example.myapplicationss.R
import com.example.myapplicationss.Repository.ArticleRepository
import com.example.myapplicationss.ViewModel.ArticleViewModel
import com.example.myapplicationss.ViewModel.ArticleViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private lateinit var mainAdapter: ArticlesAdapter
    private lateinit var avm : ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        avm = viewModel()

        if (avm.getQuery() != null){
            if (avm.getBeginDate() != null) {
                if (avm.getSort() != null) {
                    if (avm.getNewsDesk() != null) {
                        avm.getSearchQueryBeginDateSortNewsDesk(
                            avm.getQuery()!!,
                            avm.getBeginDate()!!,
                            avm.getSort()!!,
                            avm.getNewsDesk()!!
                        )
                    } else {
                        avm.getSearchQueryBeginDateSort(
                            avm.getQuery()!!,
                            avm.getBeginDate()!!,
                            avm.getSort()!!
                        )
                    }
                } else if (avm.getNewsDesk() != null) {
                    avm.getSearchQueryBeginDateNewsDesk(
                        avm.getQuery()!!,
                        avm.getBeginDate()!!,
                        avm.getNewsDesk()!!
                    )
                } else {
                    avm.getSearchQueryBeginDate(avm.getQuery()!!, avm.getBeginDate()!!)
                }
            } else if (avm.getSort() != null) {
                if (avm.getNewsDesk() != null) {
                    avm.getSearchQuerySortNewsDesk(
                        avm.getQuery()!!,
                        avm.getSort()!!,
                        avm.getNewsDesk()!!
                    )
                } else {
                    avm.getSearchQuerySort(avm.getQuery()!!, avm.getSort()!!)
                }
            } else if (avm.getNewsDesk() != null) {
                avm.getSearchQueryNewsDesk(avm.getQuery()!!, avm.getNewsDesk()!!)
            } else {
                avm.getSearchQuery(avm.getQuery()!!)
            }
        } else {
            avm.getSearch()
        }
        getAdapter()
    }

//    override fun attachBaseContext(base: Context?) {
//        super.attachBaseContext(base)
//        MultiDex.install(this)
//    }

    private fun getAdapter(){
        bindViewModel()
        mainAdapter = ArticlesAdapter()
        mainAdapter.notifyDataSetChanged()
        mainView.adapter = mainAdapter
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        mainView.layoutManager = staggeredGridLayoutManager
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

                if (getBeginDate() != 0) {
                    if (getSort() != "") {
                        if (getNewsDesk() != "") {
                            avm.getSearchQueryBeginDateSortNewsDesk(
                                query,
                                getBeginDate(),
                                getSort(),
                                getNewsDesk()
                            )
                        } else {
                            avm.getSearchQueryBeginDateSort(query, getBeginDate(), getSort())
                        }
                    } else if (getNewsDesk() != "") {
                        avm.getSearchQueryBeginDateNewsDesk(query, getBeginDate(), getNewsDesk())
                    } else {
                        avm.getSearchQueryBeginDate(query, getBeginDate())
                    }
                } else if (getSort() != "") {
                    if (getNewsDesk() != "") {
                        avm.getSearchQuerySortNewsDesk(query, getSort(), getNewsDesk())
                    } else {
                        avm.getSearchQuerySort(query, getSort())
                    }
                } else if (getNewsDesk() != "") {
                    avm.getSearchQueryNewsDesk(query, getNewsDesk())
                } else {
                    avm.getSearchQuery(query)
                }
                getAdapter()
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
}