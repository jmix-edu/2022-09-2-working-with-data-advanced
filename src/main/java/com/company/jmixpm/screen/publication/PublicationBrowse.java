package com.company.jmixpm.screen.publication;

import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Publication;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("Publication.browse")
@UiDescriptor("publication-browse.xml")
@LookupComponent("publicationsTable")
public class PublicationBrowse extends StandardLookup<Publication> {

    @Autowired
    private CollectionContainer<Publication> publicationsDc;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        List<Publication> items = publicationsDc.getItems();
    }


}