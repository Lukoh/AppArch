/*
 * Copyright (C) 2015-2017 Lukoh Nam, goForer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goforer.goforerarchblueprint.presentation.ui.view.drawer.item;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.goforer.goforerarchblueprint.presentation.ui.view.drawer.holder.IconBaseViewHolder;
import com.mikepenz.fastadapter.utils.ViewHolderFactory;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.interfaces.ColorfulBadgeable;

import java.util.List;

class PrimaryIconDrawerItem extends BasePrimaryIconDrawerItem<PrimaryIconDrawerItem,
        PrimaryIconDrawerItem.ViewHolder> implements ColorfulBadgeable<PrimaryIconDrawerItem> {
    private StringHolder mBadge;
    private BadgeStyle mBadgeStyle = new BadgeStyle();

    @Override
    public PrimaryIconDrawerItem withBadge(StringHolder badge) {
        this.mBadge = badge;
        return this;
    }

    @Override
    public PrimaryIconDrawerItem withBadge(String badge) {
        this.mBadge = new StringHolder(badge);
        return this;
    }

    @Override
    public PrimaryIconDrawerItem withBadge(@StringRes int badgeRes) {
        this.mBadge = new StringHolder(badgeRes);
        return this;
    }

    @Override
    public PrimaryIconDrawerItem withBadgeStyle(BadgeStyle badgeStyle) {
        this.mBadgeStyle = badgeStyle;
        return this;
    }

    public StringHolder getBadge() {
        return mBadge;
    }

    public BadgeStyle getBadgeStyle() {
        return mBadgeStyle;
    }

    @Override
    public int getType() {
        return com.mikepenz.materialdrawer.R.id.material_drawer_item_primary;/*"PRIMARY_ITEM"*/
    }

    @Override
    @LayoutRes
    public int getLayoutRes() {
        return com.mikepenz.materialdrawer.R.layout.material_drawer_item_primary;
    }

    @Override
    public void bindView(PrimaryIconDrawerItem.ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        Context context = holder.itemView.getContext();
        //bind the basic view parts
        bindViewHelper(holder);

        //set the text for the badge or hide
        boolean badgeVisible = StringHolder.applyToOrHide(mBadge, holder.badge);
        //style the badge if it is visible
        if (badgeVisible) {
            mBadgeStyle.style(holder.badge, getTextColorStateList(getColor(context),
                    getSelectedTextColor(context)));
            holder.badgeContainer.setVisibility(View.VISIBLE);
        } else {
            holder.badgeContainer.setVisibility(View.GONE);
        }

        //define the typeface for our textViews
        if (getTypeface() != null) {
            holder.badge.setTypeface(getTypeface());
        }

        //call the onPostBindView method to trigger post bind view actions
        // (like the listener to modify the item if required)
        onPostBindView(this, holder.itemView);
    }

    @Override
    public ViewHolderFactory<ViewHolder> getFactory() {
        return new ItemFactory();
    }

    private static class ItemFactory implements ViewHolderFactory<ViewHolder> {
        public ViewHolder create(View v) {
            return new ViewHolder(v);
        }
    }

    public static class ViewHolder extends IconBaseViewHolder {
        private View badgeContainer;
        private TextView badge;

        public ViewHolder(View view) {
            super(view);
            this.badgeContainer = view.findViewById(
                    com.mikepenz.materialdrawer.R.id.material_drawer_badge_container);
            this.badge = (TextView) view.findViewById(
                    com.mikepenz.materialdrawer.R.id.material_drawer_badge);
        }
    }
}