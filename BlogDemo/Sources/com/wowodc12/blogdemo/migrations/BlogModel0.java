package com.wowodc12.blogdemo.migrations;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class BlogModel0 extends ERXMigrationDatabase.Migration {
  @Override
  public NSArray<ERXModelVersion> modelDependencies() {
    return null;
  }
  
  @Override
  public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
    // DO NOTHING
  }

  @Override
  public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
    ERXMigrationTable authorTable = database.newTableNamed("Author");
    authorTable.newStringColumn("firstName", 100, false);
    authorTable.newIntegerColumn("id", false);
    authorTable.newStringColumn("lastName", 100, false);
    authorTable.newStringColumn("password", 50, false);
    authorTable.newStringColumn("username", 16, false);
    authorTable.create();
    authorTable.setPrimaryKey("id");

    ERXMigrationTable blogEntryTable = database.newTableNamed("BlogEntry");
    blogEntryTable.newIntegerColumn("authorID", false);
    blogEntryTable.newLargeStringColumn("content", false);
    blogEntryTable.newTimestampColumn("creationTime", false);
    blogEntryTable.newIntegerColumn("id", false);
    blogEntryTable.newTimestampColumn("lastModified", false);
    blogEntryTable.newStringColumn("title", 255, false);
    blogEntryTable.create();
    blogEntryTable.setPrimaryKey("id");

    ERXMigrationTable categoryTable = database.newTableNamed("Category");
    categoryTable.newIntegerColumn("id", false);
    categoryTable.newStringColumn("name", 50, false);
    categoryTable.create();
    categoryTable.setPrimaryKey("id");

    ERXMigrationTable categoryBlogEntryTable = database.newTableNamed("CategoryBlogEntry");
    categoryBlogEntryTable.newIntegerColumn("blogEntryId", false);
    categoryBlogEntryTable.newIntegerColumn("categoryId", false);
    categoryBlogEntryTable.create();
    categoryBlogEntryTable.setPrimaryKey("blogEntryId", "categoryId");

    blogEntryTable.addForeignKey("authorID", "Author", "id");
    categoryBlogEntryTable.addForeignKey("blogEntryId", "BlogEntry", "id");
    categoryBlogEntryTable.addForeignKey("categoryId", "Category", "id");
  }
}