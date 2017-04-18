package com.moonshot.nativedemo;

import android.Manifest;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * A placeholder fragment containing a simple view.
 */
public class DemoListActivityFragment extends Fragment implements EasyPermissions.PermissionCallbacks {

    public final static String TAG = DemoListActivityFragment.class.getSimpleName();
    private DemoAdapter mAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private DemoListListener mDemoListListener;

    protected static final String[] cameraPerms = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    protected static final int RC_CAMERA_PERMISSIONS = 102;

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        setupRecyclerView();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        setupRecyclerView();
    }

    /**
     * Interface for listening user list events.
     */
    public interface DemoListListener {
        void onDemoClicked(View view, final Demo demo);
    }

    public static DemoListActivityFragment createNew() {
        DemoListActivityFragment fragment = new DemoListActivityFragment();
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_demo_list, container, false);
        ButterKnife.bind(this, fragmentView);
        requestCameraPermissions();
        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DemoListListener) {
            this.mDemoListListener = (DemoListListener) context;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle state) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(state);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_CAMERA_PERMISSIONS)
    protected void requestCameraPermissions() {
        // Check for camera permissions
        if (!EasyPermissions.hasPermissions(getActivity(), cameraPerms)) {
            EasyPermissions.requestPermissions(this,
                    "This app needs access to your camera",
                    RC_CAMERA_PERMISSIONS, cameraPerms);
            return;
        }

        setupRecyclerView();

    }

    private void setupRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new DemoAdapter(getActivity(), R.layout.video_row, onItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.swapList(Demo.createDefaultDemos());
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        mRecyclerView.setAdapter(null);
        ButterKnife.unbind(this);
        this.mDemoListListener = null;
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    private DemoAdapter.OnItemClickListener onItemClickListener =
            new DemoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, Demo model, int position) {
                    mDemoListListener.onDemoClicked(view, model);
                }

            };

    private static class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {

        public final static String TAG = DemoAdapter.class.getSimpleName();

        private Context mContext;
        private List<Demo> mDemos = new ArrayList<>();
        private int mRowLayout;
        private final OnItemClickListener mListener;
        private int lastPosition = -1;

        public interface OnItemClickListener {
            void onItemClick(View view, Demo demo, int position);
        }

        public DemoAdapter(Context context, int rowLayout, OnItemClickListener listener) {
            this.mContext = context;
            this.mRowLayout = rowLayout;
            this.mListener = listener;
        }

        public void swapList(List<Demo> newList) {
            mDemos.clear();
            if (newList != null) {
                mDemos.addAll(newList);
            }
            this.notifyDataSetChanged();
        }

        @Override
        public DemoAdapter.DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
            return new DemoAdapter.DemoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final DemoViewHolder holder, final int position) {
            holder.titleView.setText(mDemos.get(position).getTitle());
            holder.descriptionView.setText(mDemos.get(position).getDescription());
            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        Demo item = mDemos.get(position);
                        mListener.onItemClick(v, item, position);
                    }
                }
            });

            if (!TextUtils.isEmpty(mDemos.get(position).getThumbnail())) {
                Picasso.with(mContext)
                        .load(mDemos.get(position).getThumbnail())
                        .placeholder(mContext.getResources().getDrawable(R.drawable.android_icon))
                        .centerCrop()
                        .resize(480, 270)
                        .into(holder.videoImage, new Callback() {
                            @Override
                            public void onSuccess() {
                            }
                            @Override
                            public void onError() {
                                holder.videoImage.setImageResource(R.drawable.android_icon);
                                //myImageView.setImageResource(R.drawable.default_image);
                            }
                        });
            }

        }

        @Override
        public int getItemCount() {
            return mDemos.size();
        }

        public static class DemoViewHolder extends RecyclerView.ViewHolder {

            ImageView videoImage;
            TextView titleView;
            TextView descriptionView;

            public DemoViewHolder(View itemView) {
                super(itemView);

                videoImage = (ImageView) itemView.findViewById(R.id.video_image);
                titleView = (TextView) itemView.findViewById(R.id.video_title);
                descriptionView = (TextView) itemView.findViewById(R.id.video_description);
            }

        }
    }
}
