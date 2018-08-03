package com.zony.imageloadlib.listener;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.zony.imageloadlib.ImgLoader;

/**
 * ListView or RecycleView Scroll Listener
 *
 * @param
 * @author zony
 * @time 17-9-19 上午11:21
 */
public class ListViewPauseOnScrollListener implements OnScrollListener {
	private ImgLoader imageLoader;
	private final boolean pauseOnScroll;
	private final boolean pauseOnFling;
	private final OnScrollListener externalListener;

	public ListViewPauseOnScrollListener(ImgLoader imageLoader, boolean pauseOnScroll, boolean pauseOnFling) {
		this(imageLoader, pauseOnScroll, pauseOnFling, (OnScrollListener) null);
	}

	public ListViewPauseOnScrollListener(ImgLoader imageLoader, boolean pauseOnScroll, boolean pauseOnFling,
			OnScrollListener customListener) {
		this.imageLoader = imageLoader;
		this.pauseOnScroll = pauseOnScroll;
		this.pauseOnFling = pauseOnFling;
		this.externalListener = customListener;
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		switch (scrollState) {
			case 0 :
				this.imageLoader.resumeAllTasks(view.getContext());
				break;
			case 1 :
				if (this.pauseOnScroll) {
					this.imageLoader.pauseAllTasks(view.getContext());
				}
				break;
			case 2 :
				if (this.pauseOnFling) {
					this.imageLoader.pauseAllTasks(view.getContext());
				}
		}

		if (this.externalListener != null) {
			this.externalListener.onScrollStateChanged(view, scrollState);
		}

	}

	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (this.externalListener != null) {
			this.externalListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
	}
}