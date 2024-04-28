package com.example.starylevbackendspring.utils;

public class QueriesSQL {
    private String columnName;
    private final String tableName;

    public QueriesSQL(String tableName){
        this.tableName = tableName;
    }

    public QueriesSQL(String tableName, String columnName){
        this.tableName = tableName;
        this.columnName = columnName;
    }

    public String selectAllFromTable(){
        return String.format("SELECT * FROM %s;", this.tableName);
    }

    public String selectItemsWhereColumnItemEquals(String value){
        return String.format("SELECT * FROM %s WHERE %s = '%s'", this.tableName, this.columnName, value);
    }

    public String getCountOfBooksByValues(String[] values) {
        return buildUnionQuery("SELECT COUNT(*) FROM (", values);
    }

    public String selectItemsWhereColumnItemEqualsAll(String[] values) {
        return buildUnionQuery("SELECT * FROM (", values);
    }

    public String selectItemsWhereColumnItemLike(String value){
        return "SELECT * FROM " + tableName + " WHERE LOWER(" + columnName + ") LIKE " + "'%" + value.toLowerCase() + "%'";
    }

    public String buildUnionQuery(String startQuery, String[] values) {
        StringBuilder queryBuilder = new StringBuilder(startQuery);
        for (int i = 0; i < values.length; i++) {
            String subQuery = selectItemsWhereColumnItemEquals(values[i]);
            queryBuilder.append(subQuery);

            if (i < values.length - 1) {
                queryBuilder.append(" UNION ALL ");
            }
        }
        return queryBuilder.append(") AS subquery;").toString();
    }
}
