package database;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import task.Task;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TaskDbFacade {
	private SQLiteDatabase db;

    public TaskDbFacade(SQLiteDatabase db) {
        this.db = db;
    }

    public void dispose() {
        if (db != null && db.isOpen()) {
            db.close();
        }
        db = null;
    }

    public void insert(Task t) {
        validate();
        ContentValues v = new ContentValues();
        v.put("title", t.getTitle());
        v.put("description", t.getDescription());
        v.put("reminder", t.getReminder());
        v.put("latitude", t.getLocation().getLatitude());
        v.put("longitude", t.getLocation().getLongitude());
        v.put("done", 0);

        long id = db.insert(TaskDbHelper.TABLE_TASKS, null, v);
        if (id >= 0) {
            t.setId(id);
        }
    }

    public boolean update(Task t) {
        validate();
        ContentValues v = new ContentValues();
        v.put("title", t.getTitle());
        v.put("description", t.getDescription());
        v.put("reminder", t.getReminder());
        v.put("latitude", t.getLocation().getLatitude());
        v.put("longitude", t.getLocation().getLongitude());
        v.put("done", t.done());
        
        int rowsAffected = db.update(TaskDbHelper.TABLE_TASKS, v, "_id="
                + t.getId(), null);

        return (rowsAffected == 1);
    }

    public boolean delete(Task t) {
        return delete(t.getId());
    }

    public boolean delete(long id) {
        validate();
        return (1 == db
                .delete(TaskDbHelper.TABLE_TASKS, "_id=" + id, null));
    }

    public Task getById(long id) {
        validate();
        Cursor cur = null;
        try {
            cur = db.query(true, TaskDbHelper.TABLE_TASKS, null /* all */,
                    "_id=" + id, null, null, null, null, null);
            List<Task> tmpList = new LinkedList<Task>();
            extractTasksFromCursor(tmpList, cur);
            if (tmpList != null && !tmpList.isEmpty()) {
                return tmpList.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            Log.e("topics.database", "Error searching application database.", e);
            return null;
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }
    }

    public List<Task> findByTitle(String title) {
        validate();
        List<Task> result = new LinkedList<Task>();
        Cursor cur = null;
        try {
            cur = db.query(true, TaskDbHelper.TABLE_TASKS, null /* all */,
                    "title='" + title + "'", null, null, null, "title", null);
            extractTasksFromCursor(result, cur);
        } catch (SQLException e) {
            Log.e("topics.database", "Error searching application database.", e);
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return Collections.unmodifiableList(result);
    }

    public List<Task> listAll() {
        validate();
        List<Task> result = new LinkedList<Task>();
        Cursor cur = null;
        try {
            cur = db.query(true, TaskDbHelper.TABLE_TASKS, null /* all */,
            		"done = 0", null, null, null, "title", null);
            extractTasksFromCursor(result, cur);
        } catch (SQLException e) {
            Log.e("topics.database", "Error searching application database.", e);
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return Collections.unmodifiableList(result);
    }

    public List<Task> listAllDone() {
        validate();
        List<Task> result = new LinkedList<Task>();
        Cursor cur = null;
        try {
            cur = db.query(true, TaskDbHelper.TABLE_TASKS, null/* all */,
            		"done = 1", null, null, null, "title", null);
            extractTasksFromCursor(result, cur);
        } catch (SQLException e) {
            Log.e("topics.database", "Error searching application database.", e);
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return Collections.unmodifiableList(result);
    }
    
    public Cursor getCursorForAllMovies() {
        validate();
        Cursor cur = null;
        try {
            cur = db.query(true, TaskDbHelper.TABLE_TASKS, null /* all */,
                    null, null, null, null, "title", null);
        } catch (SQLException e) {
            Log.e("topics.database", "Error searching application database.", e);
            cur = null;
        }
        return cur;
    }

    private void extractTasksFromCursor(List<Task> list, Cursor cur) {
        if (cur.moveToFirst()) {
            for (int i = 0; i < cur.getCount(); i++) {
            	Task a = new Task();
                a.setId(cur.getLong(0));
                a.setTitle(cur.getString(1));
                a.setDescription(cur.getString(2));
                a.setReminder(cur.getFloat(3));
                a.getLocation().setLatitude(cur.getFloat(4));
                a.getLocation().setLongitude(cur.getFloat(5));
                a.setDone(cur.getInt(6));
                list.add(a);

                cur.moveToNext();
            }
        }
    }

    private void validate() {
        if (db == null) {
            throw new IllegalStateException(
                    "Illegal access to the disposed MovieDbHelper object.");
        }
    }
}
