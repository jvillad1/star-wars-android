package com.jvillad.starwars.android.presentation.search.adapter.model

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.presentation.search.adapter.model.SearchInfoItemModel.SearchInfoHolder

/**
 * EpoxyModelClass for the Search info card.
 *
 * @author juan.villada
 */
@EpoxyModelClass(layout = R.layout.item_search_info)
abstract class SearchInfoItemModel : EpoxyModelWithHolder<SearchInfoHolder>() {

    override fun bind(holder: SearchInfoHolder) = with(holder) {
    }

    inner class SearchInfoHolder : EpoxyHolder() {

        override fun bindView(itemView: View) {}
    }
}
