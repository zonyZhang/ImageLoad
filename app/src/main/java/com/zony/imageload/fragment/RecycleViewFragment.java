package com.zony.imageload.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zony.imageload.R;
import com.zony.imageload.adapter.GlideRecyclerviewAdapter;
import com.zony.imageload.utils.CommonUtils;
import com.zony.imageload.utils.ImgLoadUtil;
import com.zony.imageload.utils.LogUtil;
import com.zony.imageloadlib.ImgLoader;
import com.zony.imageloadlib.listener.RecyclerViewPauseOnScrollListener;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link RecycleViewFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link RecycleViewFragment#newInstance} factory method to create an instance
 * of this fragment.
 */
public class RecycleViewFragment extends Fragment {
	private static final String TAG = "RecycleViewFragment";

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private RecyclerView recyclerView;

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	public RecycleViewFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of this fragment using the
	 * provided parameters.
	 *
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment GlideBaseFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static RecycleViewFragment newInstance(String param1, String param2) {
		RecycleViewFragment fragment = new RecycleViewFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_recycleview, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		recyclerView = (RecyclerView) view.findViewById(R.id.rv_glide);
		recyclerView.addOnScrollListener(new RecyclerViewPauseOnScrollListener(ImgLoader.getInstance(),
				true, true, new RecyclerView.OnScrollListener() {
					@Override
					public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
						super.onScrollStateChanged(recyclerView, newState);
						switch (newState) {
							case RecyclerView.SCROLL_STATE_IDLE :
								LogUtil.i(TAG, "RecyclerView.SCROLL_STATE_IDLE");
								break;
							case RecyclerView.SCROLL_STATE_DRAGGING :
								LogUtil.i(TAG, "RecyclerView.SCROLL_STATE_DRAGGING");
								break;
							case RecyclerView.SCROLL_STATE_SETTLING :
								LogUtil.i(TAG, "RecyclerView.SCROLL_STATE_SETTLING");
								break;
						}
					}

					@Override
					public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
						super.onScrolled(recyclerView, dx, dy);
					}
				}));
		initData();
	}

	private void initData() {
		// 初始化Recyclerview
		GlideRecyclerviewAdapter glideRecyclerviewAdapter = new GlideRecyclerviewAdapter(getActivity(),
				CommonUtils.urls);
		recyclerView.setAdapter(glideRecyclerviewAdapter);
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	/**
	 * This interface must be implemented by activities that contain this fragment
	 * to allow an interaction in this fragment to be communicated to the activity
	 * and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
        ImgLoadUtil.clearAll(getActivity());
	}
}
