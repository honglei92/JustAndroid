package com.boco.whl.funddemo.data.db;


import com.boco.whl.funddemo.entity.ChatErrorBean;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

/**
 * Created by xxx on 2017/6/22.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";

    public static final int VERSION = 4;

    @Migration(version = 3, database = AppDatabase.class)
    public static class Migration3 extends AlterTableMigration<ChatErrorBean> {

        public Migration3(Class<ChatErrorBean> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            super.onPreMigrate();
            addColumn(SQLiteType.INTEGER, "serverTime");
            addColumn(SQLiteType.INTEGER, "cause");
        }
    }

    @Migration(version = 4, database = AppDatabase.class)
    public static class Migration4 extends AlterTableMigration<ChatErrorBean> {

        public Migration4(Class<ChatErrorBean> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            super.onPreMigrate();
            addColumn(SQLiteType.TEXT, "badTimes");
        }
    }

}
