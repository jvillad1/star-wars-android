package com.jvillad.starwars.android.presentation.search.adapter.model

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.presentation.search.adapter.model.EmptyCharactersSearchItemModel.EmptyCharactersSearchHolder

/**
 * EpoxyModelClass for the empty / no results Characters search.
 *
 * @author juan.villada
 */
@EpoxyModelClass(layout = R.layout.item_empty_characters_search)
abstract class EmptyCharactersSearchItemModel : EpoxyModelWithHolder<EmptyCharactersSearchHolder>() {

    override fun bind(holder: EmptyCharactersSearchHolder) = with(holder) {
    }

    inner class EmptyCharactersSearchHolder : EpoxyHolder() {

        override fun bindView(itemView: View) {}
    }
}
