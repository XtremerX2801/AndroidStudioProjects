package com.example.flick.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.flick.Adapter.MainViewAdapter
import com.example.flick.Model.Result
import com.example.flick.Model.Youtube
import com.example.flick.Presenter.IMovieList
import com.example.flick.Presenter.MovieListPresenter
import com.example.flick.R
import com.example.flick.Util.onItemClickListener

class MainActivity: AppCompatActivity(), IMovieList.View {
    private var presenter: IMovieList.Presenter?
    init {
        presenter = MovieListPresenter(this)
    }
    var position = 0
    private var adapter: MainViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter?.getNowPlaying(1)
        adapter = MainViewAdapter()
        val itemClickListener = object: onItemClickListener {
            override fun onItemClick(item: Result) {
                val intent = Intent(this@MainActivity, MovieDetail::class.java)
                intent.putExtra("Movie", item)
                ContextCompat.startActivity(this@MainActivity, intent, null)
            }
        }
        adapter?.setItemClickListener(itemClickListener)

        val mainViewRecycler: RecyclerView = findViewById(R.id.mainView)

        mainViewRecycler.adapter = this.adapter
        mainViewRecycler.layoutManager = LinearLayoutManager(this)
    }

    override fun setPresenter(presenter: IMovieList.Presenter) {
        this.presenter = presenter
    }

    override fun onResponse(movie: List<Result>?, type: Int?) {
        if (movie == null) return

        val mainRecycler: RecyclerView = findViewById(R.id.mainView)

        when(type)
        {
            0->{adapter?.updateSource(movie)
            }
            1->{
                adapter?.addAll(movie)
                if(adapter!!.itemCount < position) {
                    mainRecycler.scrollToPosition(adapter!!.itemCount-1)
                }
                else
                {
                    if(position!=0) mainRecycler.scrollToPosition(position)
                }

            }
        }
    }

    override fun onFailure() {
        print("Error happens!")
    }
}