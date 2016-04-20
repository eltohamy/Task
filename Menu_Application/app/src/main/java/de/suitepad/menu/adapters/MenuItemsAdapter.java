package de.suitepad.menu.adapters;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.suitepad.menu.R;
import de.suitepad.menu.modles.MenuItem;

public class MenuItemsAdapter extends RecyclerViewCursorAdapter<MenuItemsAdapter.ViewHolder> implements View.OnClickListener {
    private final LayoutInflater layoutInflater;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public MenuItemsAdapter(final Context context) {
        super();
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.row_menu_item, viewGroup, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final Cursor cursor) {
        holder.bindData(cursor);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(final View view) {
        if (this.onItemClickListener != null) {
            final RecyclerView recyclerView = (RecyclerView) view.getParent();
            final int position = recyclerView.getChildLayoutPosition(view);
            if (position != RecyclerView.NO_POSITION) {
                final Cursor cursor = this.getItem(position);
                this.onItemClickListener.onItemClicked(cursor);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Cursor cursor);
    }
    /*
     * View.OnClickListener
     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userNameTxt;
        public TextView priceTxt;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            userNameTxt = (TextView) itemView.findViewById(R.id.userNameTxt);
            priceTxt = (TextView) itemView.findViewById(R.id.priceTxt);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(context,
                        R.anim.lift_up);
                cardView.setStateListAnimator(stateListAnimator);
            }
        }

        public void bindData(final Cursor cursor) {
            final MenuItem menuItem = MenuItem.fromCursor(cursor);
            this.userNameTxt.setText(menuItem.getName());
            this.priceTxt.setText(menuItem.getPrice() + " $");
        }
    }
}