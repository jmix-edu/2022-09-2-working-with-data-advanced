package com.company.jmixpm.screen.orderline;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.OrderLine;

@UiController("OrderLine.browse")
@UiDescriptor("order-line-browse.xml")
@LookupComponent("orderLinesTable")
public class OrderLineBrowse extends StandardLookup<OrderLine> {
}