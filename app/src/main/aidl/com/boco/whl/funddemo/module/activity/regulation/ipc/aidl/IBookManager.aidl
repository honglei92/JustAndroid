// IBookManager.aidl
package com.boco.whl.funddemo.module.activity.regulation.ipc.aidl;
import com.boco.whl.funddemo.module.activity.regulation.ipc.aidl.Book;

// Declare any non-default types here with import statements

interface IBookManager {
   List<Book> getBookList();
   void addBook(in Book book);
}
