package com.uassqlite.gilasadi.database;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.uassqlite.gilasadi.model.TableRowCount;

import static com.uassqlite.gilasadi.util.Constants.*;

public class TableRowCountQueryImplementation implements QueryContract.TableRowCountQuery {

    @Override
    public void getTableRowCount(QueryResponse<TableRowCount> response) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        try {
            long studentCount = DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_STUDENT);
            long subjectCount = DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_SUBJECT);
            long takenSubjectCount = DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_STUDENT_SUBJECT);

            TableRowCount tableRowCount = new TableRowCount(studentCount, subjectCount, takenSubjectCount);
            response.onSuccess(tableRowCount);

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }

    }
}
