package com.company.jmixpm.screen.book;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Book;

@UiController("Book.edit")
@UiDescriptor("book-edit.xml")
@EditedEntityContainer("bookDc")
public class BookEdit extends StandardEditor<Book> {
}