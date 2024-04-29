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

    public String getBookById(String id){
        return String.format("SELECT * FROM %s WHERE %s = %s", tableName, columnName, id);
    }

    public String selectItemsWhereColumnItemEqualsAll(String[] values) {
        return buildUnionQuery("SELECT * FROM (", values);
    }

    public String selectForSearchBooks(String[] columnNames, String value) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE ");

        for (int i = 0; i < columnNames.length; i++) {
            String lowerColumnValue = "LOWER(" + columnNames[i] + ")";
            String lowerValue = value.toLowerCase();

            queryBuilder.append("(").append(lowerColumnValue).append(" LIKE '%").append(lowerValue).append("%')");

            if (i < columnNames.length - 1) {
                queryBuilder.append(" OR ");
            }
        }

        return queryBuilder.toString();
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
