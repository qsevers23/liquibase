package liquibase.datatype.core

import liquibase.database.core.*
import liquibase.statement.DatabaseFunction
import spock.lang.Specification
import spock.lang.Unroll

class UUIDTypeTest extends Specification {
    @Unroll("#featureName: #object for #database")
    def "objectToSql"() {
        when:
        def type = new UUIDType()

        then:
        type.objectToSql(object, database) == expectedSql

        where:
        object                                                  | database            | expectedSql
        null                                                    | new MockDatabase()  | null
        "NULL"                                                  | new MockDatabase()  | null
        "DFD8E505-0BB7-4D3E-B341-AD17190D8C9E"                  | new MockDatabase()  | "DFD8E505-0BB7-4D3E-B341-AD17190D8C9E"
        UUID.fromString("dfd8e505-0bb7-4d3e-b341-ad17190d8c9e") | new MockDatabase()  | "dfd8e505-0bb7-4d3e-b341-ad17190d8c9e"
        null                                                    | new MSSQLDatabase() | null
        "NULL"                                                  | new MSSQLDatabase() | null
        new DatabaseFunction("NEWID()")                         | new MSSQLDatabase() | "NEWID()"
        new DatabaseFunction("NEWSEQUENTIALID()")               | new MSSQLDatabase() | "NEWSEQUENTIALID()"
        "DFD8E505-0BB7-4D3E-B341-AD17190D8C9E"                  | new MSSQLDatabase() | "'DFD8E505-0BB7-4D3E-B341-AD17190D8C9E'"
        UUID.fromString("dfd8e505-0bb7-4d3e-b341-ad17190d8c9e") | new MSSQLDatabase() | "'DFD8E505-0BB7-4D3E-B341-AD17190D8C9E'"
        null                                                    | new MySQLDatabase() | null
        "NULL"                                                  | new MySQLDatabase() | null
        new DatabaseFunction("UUID()")                          | new MySQLDatabase() | "UUID()"
        "DFD8E505-0BB7-4D3E-B341-AD17190D8C9E"                  | new MySQLDatabase() | "dfd8e505-0bb7-4d3e-b341-ad17190d8c9e"
        "dfd8e505-0bb7-4d3e-b341-ad17190d8c9e"                  | new MySQLDatabase() | "dfd8e505-0bb7-4d3e-b341-ad17190d8c9e"
        UUID.fromString("DFD8E505-0BB7-4D3E-B341-AD17190D8C9E") | new MySQLDatabase() | "dfd8e505-0bb7-4d3e-b341-ad17190d8c9e"
        UUID.fromString("dfd8e505-0bb7-4d3e-b341-ad17190d8c9e") | new MySQLDatabase() | "dfd8e505-0bb7-4d3e-b341-ad17190d8c9e"
//        "DFD8E505-0BB7-4D3E-B341-AD17190D8C9E"                  | new MySQLDatabase() | "uuid_to_bin('dfd8e505-0bb7-4d3e-b341-ad17190d8c9e')"
//        "dfd8e505-0bb7-4d3e-b341-ad17190d8c9e"                  | new MySQLDatabase() | "uuid_to_bin('dfd8e505-0bb7-4d3e-b341-ad17190d8c9e')"
//        UUID.fromString("DFD8E505-0BB7-4D3E-B341-AD17190D8C9E") | new MySQLDatabase() | "uuid_to_bin('dfd8e505-0bb7-4d3e-b341-ad17190d8c9e')"
//        UUID.fromString("dfd8e505-0bb7-4d3e-b341-ad17190d8c9e") | new MySQLDatabase() | "uuid_to_bin('dfd8e505-0bb7-4d3e-b341-ad17190d8c9e')"
    }
}
