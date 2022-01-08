package com.boco.whl.funddemo.module.activity.component;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.annotation.Nullable;
import android.util.Log;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.config.Constant;

/**
 * contentprovider使用实践
 *
 * @author:honglei92
 * @time:2018/7/27
 */
public class EasyCommonActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_easy_commom;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentProviderTest();

    }

    private void contentProviderTest() {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            // 获取联系人姓名
            StringBuilder sb = new StringBuilder();
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            sb.append("contactId=").append(contactId).append(",name=").append(name);

            //获取联系人手机号码
            Cursor phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null, null);
            while (phones.moveToNext()) {
                String phone = phones.getString(phones.getColumnIndex("data1"));
                sb.append(",phone=").append(phone);
            }

            //获取联系人email
            Cursor emails = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
                    null, null);
            while (emails.moveToNext()) {
                String email = emails.getString(emails.getColumnIndex("data1"));
                sb.append(",email=").append(email);
            }
            Log.i(Constant.TAG, sb.toString());
        }
    }
}
