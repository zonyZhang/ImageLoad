package com.zony.imageloadlib.listener;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import com.zony.imageloadlib.ImgLoader;

/**
 * ListView or RecycleView Scroll Listener
 *
 * @param
 * @author zony
 * @time 17-9-19 上午11:21
 */
public class RecyclerViewPauseOnScrollListener extends OnScrollListener {
	private ImgLoader imageLoader;
	private final boolean pauseOnScroll;
	private final boolean pauseOnDragging;
	private final OnScrollListener externalListener;

	public RecyclerViewPauseOnScrollListener(ImgLoader imageLoader, boolean pauseOnScroll, boolean pauseOnDragging) {
		this(imageLoader, pauseOnScroll, pauseOnDragging, (OnScrollListener) null);
	}

	public RecyclerViewPauseOnScrollListener(ImgLoader imageLoader, boolean pauseOnScroll, boolean pauseOnDragging,
			OnScrollListener customListener) {
		this.imageLoader = imageLoader;
		this.pauseOnScroll = pauseOnScroll;
		this.pauseOnDragging = pauseOnDragging;
		this.externalListener = customListener;
	}

	@Override
	public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
		super.onScrollStateChanged(recyclerView, newState);
		switch (newState) {
			case RecyclerView.SCROLL_STATE_IDLE :
				this.imageLoader.resumeAllTasks(recyclerView.getContext());
				break;
			case RecyclerView.SCROLL_STATE_DRAGGING :
				if (this.pauseOnDragging) {
					this.imageLoader.pauseAllTasks(recyclerView.getContext());
				}
				break;
			case RecyclerView.SCROLL_STATE_SETTLING :
				if (this.pauseOnScroll) {
					this.imageLoader.pauseAllTasks(recyclerView.getContext());
				}
				break;
		}
		if (this.externalListener != null) {
			this.externalListener.onScrollStateChanged(recyclerView, newState);
		}
	}

	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
		super.onScrolled(recyclerView, dx, dy);
		if (externalListener != null) {
			externalListener.onScrolled(recyclerView, dx, dy);
		}
	}
}