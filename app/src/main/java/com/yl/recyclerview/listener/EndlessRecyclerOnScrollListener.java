package com.yl.recyclerview.listener;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

/**
 * RecyclerView滑动监听
 * Created by yangle on 2017/10/12.
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    // 用来标记是否正在向上滑动
    private boolean isSlidingUpward = false;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        // 当不滑动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            // 获取最后一个完全显示的itemPosition
            int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
            int itemCount = manager.getItemCount();



            // 判断是否滑动到了最后一个item，并且是向上滑动
            if (lastItemPosition >= (itemCount - 2) && isSlidingUpward ) {
                int height =((RecyclerView.LayoutParams)manager.findViewByPosition(itemCount-1).getLayoutParams()).height;
                Rect rect = new Rect();
                manager.findViewByPosition(itemCount-1).getLocalVisibleRect(rect);
                if(rect.bottom == height) {
                    // 加载更多
                    onLoadMore();
                }
                System.out.println("bajie dy =" + dy);
                System.out.println("bajie dy =" + lastItemPosition);
                System.out.println("bajie dy =" + height);
                System.out.println("bajie dy =" + rect.top);
                System.out.println("bajie dy =" + rect.bottom);
                // 加载更多
//                onLoadMore();
            }
        }
    }

    private int dy;
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        this.dy = dy;
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        isSlidingUpward = dy > 0;
    }

    /**
     * 加载更多回调
     */
    public abstract void onLoadMore();

}
