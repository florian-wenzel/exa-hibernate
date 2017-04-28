package com.exasol.dialect;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.JDBCException;
import org.hibernate.LockOptions;
import org.hibernate.PessimisticLockException;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.spi.SQLExceptionConversionDelegate;
import org.hibernate.exception.spi.TemplatedViolatedConstraintNameExtracter;
import org.hibernate.exception.spi.ViolatedConstraintNameExtracter;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.internal.util.JdbcExceptionHelper;
import org.hibernate.type.StandardBasicTypes;


/**
 * An SQL dialect for EXASOL
 *
 */
public class ExasolDialect extends Dialect {

	public ExasolDialect() {
		super();

		registerColumnType( Types.DATE, "date" );
		registerColumnType( Types.TIME, "date" );
		registerColumnType( Types.TIMESTAMP, "timestamp" );
		registerColumnType( Types.CHAR, "char(1)" );
		registerColumnType( Types.VARCHAR, 4000, "varchar($l)" );
		registerColumnType( Types.DATE, "date" );
		registerColumnType( Types.TIME, "timestamp" );
		registerColumnType( Types.TIMESTAMP, "timestamp" );
		registerColumnType( Types.BOOLEAN, "boolean" );
		registerColumnType( Types.BIT, "boolean" );
		registerColumnType( Types.BIGINT, "decimal(36,0)" );
		registerColumnType( Types.SMALLINT, "decimal(5,0)" );
		registerColumnType( Types.TINYINT, "decimal(3,0)" );
		registerColumnType( Types.INTEGER, "decimal(10,0)" );
		registerColumnType( Types.FLOAT, "float" );
		registerColumnType( Types.DOUBLE, "double precision" );
		registerColumnType( Types.NUMERIC, "decimal($p,$s)" );
		registerColumnType( Types.DECIMAL, "decimal($p,$s)" );

		registerFunction( "abs", new StandardSQLFunction("abs") );
		registerFunction( "acos", new StandardSQLFunction("acos", StandardBasicTypes.DOUBLE) );
		registerFunction( "add_days", new StandardSQLFunction("add_days") ); // Date or timestamp depending on the parameter
		registerFunction( "add_hours", new StandardSQLFunction("add_hours", StandardBasicTypes.TIMESTAMP) );
		registerFunction( "add_minutes", new StandardSQLFunction("add_minutes", StandardBasicTypes.TIMESTAMP) );
		registerFunction( "add_months", new StandardSQLFunction("add_months") );  // Date or timestamp depending on the parameter
		registerFunction( "add_seconds", new StandardSQLFunction("add_seconds", StandardBasicTypes.TIMESTAMP) );
		registerFunction( "add_years", new StandardSQLFunction("add_years") );  // Date or timestamp depending on the parameter
		registerFunction( "ascii", new StandardSQLFunction("ascii", StandardBasicTypes.INTEGER) );
		registerFunction( "asin", new StandardSQLFunction("asin", StandardBasicTypes.DOUBLE) );
		registerFunction( "atan", new StandardSQLFunction("atan", StandardBasicTypes.DOUBLE) );
		registerFunction( "atan2", new StandardSQLFunction("atan2", StandardBasicTypes.DOUBLE) );
		registerFunction( "avg", new StandardSQLFunction("avg" , StandardBasicTypes.DOUBLE) ); 
		registerFunction( "bit_and", new StandardSQLFunction("bit_and", StandardBasicTypes.BIG_INTEGER) ); 
		registerFunction( "bit_check", new StandardSQLFunction("bit_check", StandardBasicTypes.BOOLEAN) ); 
		registerFunction( "bit_length", new StandardSQLFunction("bit_length", StandardBasicTypes.INTEGER) ); 
		registerFunction( "bit_not", new StandardSQLFunction("bit_not", StandardBasicTypes.BIG_INTEGER) ); 
		registerFunction( "bit_or", new StandardSQLFunction("bit_or", StandardBasicTypes.BIG_INTEGER) ); 
		registerFunction( "bit_set", new StandardSQLFunction("bit_set", StandardBasicTypes.BIG_INTEGER) ); 
		registerFunction( "bit_to_num", new StandardSQLFunction("bit_to_num", StandardBasicTypes.BIG_INTEGER) ); 
		registerFunction( "bit_xor", new StandardSQLFunction("bit_xor", StandardBasicTypes.BIG_INTEGER) ); 
		registerFunction( "cast", new StandardSQLFunction("cast") ); // Type depends on parameter
		registerFunction( "ceil", new StandardSQLFunction("ceil") ); // Type depends on parameter
		registerFunction( "character_length", new StandardSQLFunction("character_length", StandardBasicTypes.INTEGER) );
		registerFunction( "chr", new StandardSQLFunction("chr", StandardBasicTypes.CHARACTER) );
		registerFunction( "coalesce", new StandardSQLFunction("coalesce") );
		registerFunction( "concat", new VarArgsSQLFunction(StandardBasicTypes.STRING, "", "||", "") );
		registerFunction( "cos", new StandardSQLFunction("cos", StandardBasicTypes.DOUBLE) );
		registerFunction( "cosh", new StandardSQLFunction("cosh", StandardBasicTypes.DOUBLE) );
		registerFunction( "count", new StandardSQLFunction("count", StandardBasicTypes.INTEGER) );
		registerFunction( "current_date", new NoArgSQLFunction("current_date", StandardBasicTypes.DATE, false) );
		registerFunction( "current_schema", new NoArgSQLFunction("current_schema", StandardBasicTypes.STRING, false) );
		registerFunction( "current_session", new NoArgSQLFunction("current_session", StandardBasicTypes.STRING, false) );
		registerFunction( "current_timestamp", new NoArgSQLFunction("current_timestamp", StandardBasicTypes.TIMESTAMP, false) );
		registerFunction( "current_user", new NoArgSQLFunction("current_user", StandardBasicTypes.STRING, false) );
		registerFunction( "days_between", new StandardSQLFunction("days_between", StandardBasicTypes.DOUBLE) ); 
		registerFunction( "decode", new StandardSQLFunction("decode") ); // Type depends on parameter
		registerFunction( "dense_rank", new StandardSQLFunction("dense_rank", StandardBasicTypes.BIG_INTEGER) );
		registerFunction( "dump", new StandardSQLFunction("dump") ); // Type depends on parameter
		registerFunction( "exp", new StandardSQLFunction("exp", StandardBasicTypes.DOUBLE) );
		registerFunction( "extract", new StandardSQLFunction("extract", StandardBasicTypes.INTEGER) );
		registerFunction( "first", new StandardSQLFunction("first") ); // Type depends on parameter
		registerFunction( "floor", new StandardSQLFunction("floor") );
		registerFunction( "from_posix_time", new StandardSQLFunction("from_posix_time", StandardBasicTypes.TIMESTAMP) );
		registerFunction( "greatest", new StandardSQLFunction("greatest") );
		registerFunction( "group_concat", new StandardSQLFunction("group_concat") );
		registerFunction( "hash_tiger", new NoArgSQLFunction("hash_tiger", StandardBasicTypes.STRING, false) );
		registerFunction( "hours_between", new StandardSQLFunction("hours_between", StandardBasicTypes.DOUBLE) ); 
		registerFunction( "instr", new StandardSQLFunction("instr", StandardBasicTypes.INTEGER) );
		registerFunction( "lag", new StandardSQLFunction("lag") );
		registerFunction( "lead", new StandardSQLFunction("lead") );
		registerFunction( "least", new StandardSQLFunction("least") );
		registerFunction( "length", new StandardSQLFunction("length", StandardBasicTypes.INTEGER) );
		registerFunction( "ln", new StandardSQLFunction("ln", StandardBasicTypes.DOUBLE) );
		registerFunction( "localtimestamp", new NoArgSQLFunction("localtimestamp", StandardBasicTypes.TIMESTAMP, false) );
		registerFunction( "log", new StandardSQLFunction("log", StandardBasicTypes.DOUBLE) );
		registerFunction( "lower", new StandardSQLFunction("lower") );
		registerFunction( "lpad", new StandardSQLFunction("lpad", StandardBasicTypes.STRING) );
		registerFunction( "ltrim", new StandardSQLFunction("ltrim", StandardBasicTypes.STRING) );
		registerFunction( "max", new StandardSQLFunction("max") ); // Type depends on parameter
		registerFunction( "min", new StandardSQLFunction("min") ); // Type depends on parameter
		registerFunction( "minutes_between", new StandardSQLFunction("minutes_between", StandardBasicTypes.DOUBLE) ); 
		registerFunction( "mod", new StandardSQLFunction("mod", StandardBasicTypes.INTEGER) );
		registerFunction( "months_between", new StandardSQLFunction("months_between", StandardBasicTypes.DOUBLE) ); 
		registerFunction( "nullif", new StandardSQLFunction("nullif") ); // Type depends on parameter
		registerFunction( "nullifzero", new StandardSQLFunction("nullifzero") ); // Type depends on parameter
		registerFunction( "nvl", new StandardSQLFunction("nvl") );
		registerFunction( "octet_length", new StandardSQLFunction("octet_length", StandardBasicTypes.INTEGER) );
		registerFunction( "over", new StandardSQLFunction("over") ); // after lag or lead 
		registerFunction( "position", new StandardSQLFunction("position", StandardBasicTypes.INTEGER) );
		registerFunction( "posix_time", new StandardSQLFunction("posix_time", StandardBasicTypes.TIMESTAMP) );
		registerFunction( "power", new StandardSQLFunction("power") );
		registerFunction( "rank", new StandardSQLFunction("rank", StandardBasicTypes.BIG_INTEGER) );
		registerFunction( "replace", new StandardSQLFunction("replace", StandardBasicTypes.STRING) );
		registerFunction( "round", new StandardSQLFunction("round") );
		registerFunction( "row_number", new NoArgSQLFunction("row_number", StandardBasicTypes.LONG, false) );
		registerFunction( "rowid", new NoArgSQLFunction("rowid", StandardBasicTypes.LONG, false) );
		registerFunction( "rpad", new StandardSQLFunction("rpad", StandardBasicTypes.STRING) );
		registerFunction( "rtrim", new StandardSQLFunction("rtrim") );
		registerFunction( "seconds_between", new StandardSQLFunction("seconds_between", StandardBasicTypes.DOUBLE) ); 
		registerFunction( "sign", new StandardSQLFunction("sign", StandardBasicTypes.INTEGER) );
		registerFunction( "sin", new StandardSQLFunction("sin", StandardBasicTypes.DOUBLE) );
		registerFunction( "sinh", new StandardSQLFunction("sinh", StandardBasicTypes.DOUBLE) );
		registerFunction( "sqrt", new StandardSQLFunction("sqrt", StandardBasicTypes.DOUBLE) );
		registerFunction( "stddev", new StandardSQLFunction("stddev", StandardBasicTypes.DOUBLE) );
		registerFunction( "stddev_pop", new StandardSQLFunction("stddev_pop", StandardBasicTypes.DOUBLE) );
		registerFunction( "stddev_samp", new StandardSQLFunction("stddev_samp", StandardBasicTypes.DOUBLE) );
		registerFunction( "substr", new StandardSQLFunction("substr", StandardBasicTypes.STRING) );
		registerFunction( "sum", new StandardSQLFunction("sum") ); // Type depends on parameter
		registerFunction( "sysdate", new NoArgSQLFunction("sysdate", StandardBasicTypes.DATE, false) );
		registerFunction( "systimestamp", new NoArgSQLFunction("systimestamp", StandardBasicTypes.TIMESTAMP, false) );
		registerFunction( "tan", new StandardSQLFunction("tan", StandardBasicTypes.DOUBLE) );
		registerFunction( "tanh", new StandardSQLFunction("tanh", StandardBasicTypes.DOUBLE) );
		registerFunction( "to_char", new StandardSQLFunction("to_char", StandardBasicTypes.STRING) );
		registerFunction( "to_date", new StandardSQLFunction("to_date", StandardBasicTypes.DATE) );
		registerFunction( "to_number", new StandardSQLFunction("to_number") );
		registerFunction( "to_timestamp", new StandardSQLFunction("to_timestamp", StandardBasicTypes.TIMESTAMP) );
		registerFunction( "translate", new StandardSQLFunction("translate", StandardBasicTypes.STRING) );
		registerFunction( "trim", new StandardSQLFunction("trim", StandardBasicTypes.STRING) );
		registerFunction( "trunc", new StandardSQLFunction("trunc") );
		registerFunction( "unicodechr", new StandardSQLFunction("unicodechr", StandardBasicTypes.CHARACTER) );
		registerFunction( "unicode", new StandardSQLFunction("unicode", StandardBasicTypes.INTEGER) );
		registerFunction( "upper", new StandardSQLFunction("upper", StandardBasicTypes.STRING) );
		registerFunction( "user", new NoArgSQLFunction("user", StandardBasicTypes.STRING, false) );
		registerFunction( "var_pop", new StandardSQLFunction("var_pop", StandardBasicTypes.DOUBLE) );
		registerFunction( "var_samp", new StandardSQLFunction("var_samp", StandardBasicTypes.DOUBLE) );
		registerFunction( "variance", new StandardSQLFunction("variance", StandardBasicTypes.DOUBLE) );
		registerFunction( "years_between", new StandardSQLFunction("years_between", StandardBasicTypes.DOUBLE) ); 
		registerFunction( "zeroifnull", new StandardSQLFunction("zeroifnull") ); // Type depends on parameter

		getDefaultProperties().setProperty( Environment.STATEMENT_BATCH_SIZE, NO_BATCH );
        registerExasolKeywords();
	}


    private void registerExasolKeywords() {
        registerKeyword( "add" );
        registerKeyword( "absolute" );
        registerKeyword( "abs" );
        registerKeyword( "acos" );
        registerKeyword( "action" );
        registerKeyword( "add" );
        registerKeyword( "add_days" );
        registerKeyword( "add_hours" );
        registerKeyword( "add_minutes" );
        registerKeyword( "add_months" );
        registerKeyword( "add_seconds" );
        registerKeyword( "add_weeks" );
        registerKeyword( "add_years" );
        registerKeyword( "admin" );
        registerKeyword( "after" );
        registerKeyword( "align" );
        registerKeyword( "all" );
        registerKeyword( "allocate" );
        registerKeyword( "alter" );
        registerKeyword( "always" );
        registerKeyword( "analyze" );
        registerKeyword( "and" );
        registerKeyword( "ansi" );
        registerKeyword( "any" );
        registerKeyword( "append" );
        registerKeyword( "are" );
        registerKeyword( "array" );
        registerKeyword( "as" );
        registerKeyword( "asc" );
        registerKeyword( "ascii" );
        registerKeyword( "asensitive" );
        registerKeyword( "asin" );
        registerKeyword( "assertion" );
        registerKeyword( "assignment" );
        registerKeyword( "asymmetric" );
        registerKeyword( "at" );
        registerKeyword( "atan" );
        registerKeyword( "atan2" );
        registerKeyword( "atomic" );
        registerKeyword( "attribute" );
        registerKeyword( "audit" );
        registerKeyword( "authenticated" );
        registerKeyword( "authid" );
        registerKeyword( "authorization" );
        registerKeyword( "auto" );
        registerKeyword( "avg" );
        registerKeyword( "backup" );
        registerKeyword( "before" );
        registerKeyword( "begin" );
        registerKeyword( "bernoulli" );
        registerKeyword( "between" );
        registerKeyword( "bigint" );
        registerKeyword( "binary" );
        registerKeyword( "bit" );
        registerKeyword( "bit_and" );
        registerKeyword( "bit_check" );
        registerKeyword( "bit_length" );
        registerKeyword( "bit_not" );
        registerKeyword( "bit_or" );
        registerKeyword( "bit_set" );
        registerKeyword( "bit_to_num" );
        registerKeyword( "bit_xor" );
        registerKeyword( "blob" );
        registerKeyword( "blocked" );
        registerKeyword( "bool" );
        registerKeyword( "boolean" );
        registerKeyword( "both" );
        registerKeyword( "breadth" );
        registerKeyword( "by" );
        registerKeyword( "byte" );
        registerKeyword( "call" );
        registerKeyword( "called" );
        registerKeyword( "cardinality" );
        registerKeyword( "cascade" );
        registerKeyword( "cascaded" );
        registerKeyword( "case" );
        registerKeyword( "casespecific" );
        registerKeyword( "cast" );
        registerKeyword( "catalog" );
        registerKeyword( "ceil" );
        registerKeyword( "ceiling" );
        registerKeyword( "chain" );
        registerKeyword( "change" );
        registerKeyword( "char" );
        registerKeyword( "character" );
        registerKeyword( "character_length" );
        registerKeyword( "characteristics" );
        registerKeyword( "characters" );
        registerKeyword( "character_set_catalog" );
        registerKeyword( "character_set_name" );
        registerKeyword( "character_set_schema" );
        registerKeyword( "check" );
        registerKeyword( "checked" );
        registerKeyword( "chr" );
        registerKeyword( "clear" );
        registerKeyword( "clob" );
        registerKeyword( "close" );
        registerKeyword( "coalesce" );
        registerKeyword( "cobol" );
        registerKeyword( "collate" );
        registerKeyword( "collation" );
        registerKeyword( "collation_catalog" );
        registerKeyword( "collation_name" );
        registerKeyword( "collation_schema" );
        registerKeyword( "cologne_phonetic" );
        registerKeyword( "column" );
        registerKeyword( "commit" );
        registerKeyword( "comment" );
        registerKeyword( "comments" );
        registerKeyword( "committed" );
        registerKeyword( "concat" );
        registerKeyword( "condition" );
        registerKeyword( "connect" );
        registerKeyword( "connection" );
        registerKeyword( "constant" );
        registerKeyword( "constraint" );
        registerKeyword( "constraint_state_default" );
        registerKeyword( "constraints" );
        registerKeyword( "constructor" );
        registerKeyword( "contains" );
        registerKeyword( "continue" );
        registerKeyword( "control" );
        registerKeyword( "convert" );
        registerKeyword( "convert_tz" );
        registerKeyword( "corresponding" );
        registerKeyword( "cos" );
        registerKeyword( "cosh" );
        registerKeyword( "cot" );
        registerKeyword( "count" );
        registerKeyword( "covar_pop" );
        registerKeyword( "covar_samp" );
        registerKeyword( "corr" );
        registerKeyword( "create" );
        registerKeyword( "created" );
        registerKeyword( "cross" );
        registerKeyword( "cs" );
        registerKeyword( "csv" );
        registerKeyword( "cube" );
        registerKeyword( "curdate" );
        registerKeyword( "current" );
        registerKeyword( "current_date" );
        registerKeyword( "current_path" );
        registerKeyword( "current_role" );
        registerKeyword( "current_schema" );
        registerKeyword( "current_session" );
        registerKeyword( "current_statement" );
        registerKeyword( "current_time" );
        registerKeyword( "current_timestamp" );
        registerKeyword( "current_user" );
        registerKeyword( "cursor" );
        registerKeyword( "cycle" );
        registerKeyword( "data" );
        registerKeyword( "database" );
        registerKeyword( "datalink" );
        registerKeyword( "date" );
        registerKeyword( "datetime_interval_code" );
        registerKeyword( "datetime_interval_precision" );
        registerKeyword( "date_trunc" );
        registerKeyword( "day" );
        registerKeyword( "days_between" );
        registerKeyword( "dbtimezone" );
        registerKeyword( "deallocate" );
        registerKeyword( "dec" );
        registerKeyword( "decimal" );
        registerKeyword( "declare" );
        registerKeyword( "decode" );
        registerKeyword( "default" );
        registerKeyword( "defaults" );
        registerKeyword( "deferrable" );
        registerKeyword( "deferred" );
        registerKeyword( "defined" );
        registerKeyword( "definer" );
        registerKeyword( "degrees" );
        registerKeyword( "delete" );
        registerKeyword( "delimit" );
        registerKeyword( "delimiter" );
        registerKeyword( "dense_rank" );
        registerKeyword( "depth" );
        registerKeyword( "deref" );
        registerKeyword( "derived" );
        registerKeyword( "desc" );
        registerKeyword( "describe" );
        registerKeyword( "descriptor" );
        registerKeyword( "deterministic" );
        registerKeyword( "diagnostics" );
        registerKeyword( "dictionary" );
        registerKeyword( "disable" );
        registerKeyword( "disabled" );
        registerKeyword( "disconnect" );
        registerKeyword( "dispatch" );
        registerKeyword( "distinct" );
        registerKeyword( "distribute" );
        registerKeyword( "distribution" );
        registerKeyword( "div" );
        registerKeyword( "dlurlcomplete" );
        registerKeyword( "dlurlpath" );
        registerKeyword( "dlurlpathonly" );
        registerKeyword( "dlurlscheme" );
        registerKeyword( "dlurlserver" );
        registerKeyword( "dlvalue" );
        registerKeyword( "do" );
        registerKeyword( "domain" );
        registerKeyword( "double" );
        registerKeyword( "down" );
        registerKeyword( "drop" );
        registerKeyword( "dump" );
        registerKeyword( "dynamic" );
        registerKeyword( "dynamic_function" );
        registerKeyword( "dynamic_function_code" );
        registerKeyword( "each" );
        registerKeyword( "edit_distance" );
        registerKeyword( "else" );
        registerKeyword( "elseif" );
        registerKeyword( "elsif" );
        registerKeyword( "emits" );
        registerKeyword( "enable" );
        registerKeyword( "enabled" );
        registerKeyword( "encoding" );
        registerKeyword( "end" );
        registerKeyword( "end-exec" );
        registerKeyword( "enforce" );
        registerKeyword( "equals" );
        registerKeyword( "errors" );
        registerKeyword( "escape" );
        registerKeyword( "estimate" );
        registerKeyword( "evaluate" );
        registerKeyword( "exa" );
        registerKeyword( "except" );
        registerKeyword( "exception" );
        registerKeyword( "exclude" );
        registerKeyword( "excluding" );
        registerKeyword( "exec" );
        registerKeyword( "execute" );
        registerKeyword( "exists" );
        registerKeyword( "exit" );
        registerKeyword( "exp" );
        registerKeyword( "export" );
        registerKeyword( "expression" );
        registerKeyword( "external" );
        registerKeyword( "extract" );
        registerKeyword( "false" );
        registerKeyword( "fbv" );
        registerKeyword( "fetch" );
        registerKeyword( "file" );
        registerKeyword( "final" );
        registerKeyword( "first" );
        registerKeyword( "first_value" );
        registerKeyword( "float" );
        registerKeyword( "floor" );
        registerKeyword( "flush" );
        registerKeyword( "following" );
        registerKeyword( "for" );
        registerKeyword( "forall" );
        registerKeyword( "force" );
        registerKeyword( "foreign" );
        registerKeyword( "format" );
        registerKeyword( "fortran" );
        registerKeyword( "found" );
        registerKeyword( "free" );
        registerKeyword( "from" );
        registerKeyword( "from_posix_time" );
        registerKeyword( "fs" );
        registerKeyword( "full" );
        registerKeyword( "function" );
        registerKeyword( "general" );
        registerKeyword( "generated" );
        registerKeyword( "geometry" );
        registerKeyword( "get" );
        registerKeyword( "global" );
        registerKeyword( "go" );
        registerKeyword( "goto" );
        registerKeyword( "grant" );
        registerKeyword( "granted" );
        registerKeyword( "greatest" );
        registerKeyword( "group" );
        registerKeyword( "grouping" );
        registerKeyword( "grouping_id" );
        registerKeyword( "group_concat" );
        registerKeyword( "handler" );
        registerKeyword( "has" );
        registerKeyword( "hash" );
        registerKeyword( "hash_md5" );
        registerKeyword( "hash_sha" );
        registerKeyword( "hash_sha1" );
        registerKeyword( "hash_tiger" );
        registerKeyword( "having" );
        registerKeyword( "hierarchy" );
        registerKeyword( "hold" );
        registerKeyword( "hour" );
        registerKeyword( "hours_between" );
        registerKeyword( "identified" );
        registerKeyword( "identity" );
        registerKeyword( "if" );
        registerKeyword( "ifnull" );
        registerKeyword( "ignore" );
        registerKeyword( "immediate" );
        registerKeyword( "implementation" );
        registerKeyword( "import" );
        registerKeyword( "in" );
        registerKeyword( "including" );
        registerKeyword( "index" );
        registerKeyword( "indicator" );
        registerKeyword( "initially" );
        registerKeyword( "inner" );
        registerKeyword( "inout" );
        registerKeyword( "input" );
        registerKeyword( "insensitive" );
        registerKeyword( "insert" );
        registerKeyword( "instance" );
        registerKeyword( "instantiable" );
        registerKeyword( "instr" );
        registerKeyword( "int" );
        registerKeyword( "integer" );
        registerKeyword( "integrity" );
        registerKeyword( "intersect" );
        registerKeyword( "interval" );
        registerKeyword( "into" );
        registerKeyword( "invalid" );
        registerKeyword( "invoker" );
        registerKeyword( "is" );
        registerKeyword( "is_boolean" );
        registerKeyword( "is_date" );
        registerKeyword( "is_dsinterval" );
        registerKeyword( "is_number" );
        registerKeyword( "is_timestamp" );
        registerKeyword( "is_yminterval" );
        registerKeyword( "isolation" );
        registerKeyword( "iterate" );
        registerKeyword( "join" );
        registerKeyword( "keep" );
        registerKeyword( "key" );
        registerKeyword( "keys" );
        registerKeyword( "key_member" );
        registerKeyword( "key_type" );
        registerKeyword( "kill" );
        registerKeyword( "lag" );
        registerKeyword( "language" );
        registerKeyword( "large" );
        registerKeyword( "last" );
        registerKeyword( "last_value" );
        registerKeyword( "lateral" );
        registerKeyword( "lcase" );
        registerKeyword( "ldap" );
        registerKeyword( "lead" );
        registerKeyword( "leading" );
        registerKeyword( "least" );
        registerKeyword( "leave" );
        registerKeyword( "left" );
        registerKeyword( "length" );
        registerKeyword( "level" );
        registerKeyword( "like" );
        registerKeyword( "limit" );
        registerKeyword( "link" );
        registerKeyword( "ln" );
        registerKeyword( "locate" );
        registerKeyword( "local" );
        registerKeyword( "localtime" );
        registerKeyword( "localtimestamp" );
        registerKeyword( "locator" );
        registerKeyword( "lock" );
        registerKeyword( "log" );
        registerKeyword( "log2" );
        registerKeyword( "log10" );
        registerKeyword( "logs" );
        registerKeyword( "long" );
        registerKeyword( "longvarchar" );
        registerKeyword( "loop" );
        registerKeyword( "lower" );
        registerKeyword( "lpad" );
        registerKeyword( "ltrim" );
        registerKeyword( "lua" );
        registerKeyword( "map" );
        registerKeyword( "match" );
        registerKeyword( "matched" );
        registerKeyword( "max" );
        registerKeyword( "maximal" );
        registerKeyword( "median" );
        registerKeyword( "merge" );
        registerKeyword( "method" );
        registerKeyword( "mid" );
        registerKeyword( "min" );
        registerKeyword( "minus" );
        registerKeyword( "minute" );
        registerKeyword( "minutes_between" );
        registerKeyword( "mod" );
        registerKeyword( "modifies" );
        registerKeyword( "modify" );
        registerKeyword( "module" );
        registerKeyword( "month" );
        registerKeyword( "months_between" );
        registerKeyword( "mumps" );
        registerKeyword( "names" );
        registerKeyword( "national" );
        registerKeyword( "natural" );
        registerKeyword( "nchar" );
        registerKeyword( "nclob" );
        registerKeyword( "never" );
        registerKeyword( "new" );
        registerKeyword( "next" );
        registerKeyword( "nls_date_format" );
        registerKeyword( "nls_date_language" );
        registerKeyword( "nls_first_day_of_week" );
        registerKeyword( "nls_numeric_characters" );
        registerKeyword( "nls_timestamp_format" );
        registerKeyword( "no" );
        registerKeyword( "nologging" );
        registerKeyword( "none" );
        registerKeyword( "normalized" );
        registerKeyword( "not" );
        registerKeyword( "now" );
        registerKeyword( "null" );
        registerKeyword( "nullif" );
        registerKeyword( "nullifzero" );
        registerKeyword( "nulls" );
        registerKeyword( "number" );
        registerKeyword( "numeric" );
        registerKeyword( "numtodsinterval" );
        registerKeyword( "numtoyminterval" );
        registerKeyword( "nvl" );
        registerKeyword( "object" );
        registerKeyword( "octets" );
        registerKeyword( "octet_length" );
        registerKeyword( "of" );
        registerKeyword( "off" );
        registerKeyword( "offset" );
        registerKeyword( "old" );
        registerKeyword( "on" );
        registerKeyword( "only" );
        registerKeyword( "open" );
        registerKeyword( "optimize" );
        registerKeyword( "option" );
        registerKeyword( "options" );
        registerKeyword( "or" );
        registerKeyword( "ora" );
        registerKeyword( "order" );
        registerKeyword( "ordering" );
        registerKeyword( "ordinality" );
        registerKeyword( "others" );
        registerKeyword( "out" );
        registerKeyword( "outer" );
        registerKeyword( "output" );
        registerKeyword( "over" );
        registerKeyword( "overlaps" );
        registerKeyword( "overlay" );
        registerKeyword( "overriding" );
        registerKeyword( "owner" );
        registerKeyword( "pad" );
        registerKeyword( "padding" );
        registerKeyword( "parallel_enable" );
        registerKeyword( "parameter" );
        registerKeyword( "parameter_specific_catalog" );
        registerKeyword( "parameter_specific_name" );
        registerKeyword( "parameter_specific_schema" );
        registerKeyword( "partial" );
        registerKeyword( "partition" );
        registerKeyword( "pascal" );
        registerKeyword( "path" );
        registerKeyword( "percentile_cont" );
        registerKeyword( "percentile_disc" );
        registerKeyword( "permission" );
        registerKeyword( "pi" );
        registerKeyword( "placing" );
        registerKeyword( "pli" );
        registerKeyword( "position" );
        registerKeyword( "posix_time" );
        registerKeyword( "power" );
        registerKeyword( "preceding" );
        registerKeyword( "precision" );
        registerKeyword( "preload" );
        registerKeyword( "prepare" );
        registerKeyword( "preserve" );
        registerKeyword( "primary" );
        registerKeyword( "prior" );
        registerKeyword( "priority" );
        registerKeyword( "privilege" );
        registerKeyword( "privileges" );
        registerKeyword( "procedure" );
        registerKeyword( "profile" );
        registerKeyword( "python" );
        registerKeyword( "scheme" );
        registerKeyword( "javascript" );
        registerKeyword( "query_timeout" );
        registerKeyword( "r" );
        registerKeyword( "radians" );
        registerKeyword( "rand" );
        registerKeyword( "random" );
        registerKeyword( "range" );
        registerKeyword( "rank" );
        registerKeyword( "ratio_to_report" );
        registerKeyword( "read" );
        registerKeyword( "reads" );
        registerKeyword( "real" );
        registerKeyword( "recompress" );
        registerKeyword( "record" );
        registerKeyword( "recovery" );
        registerKeyword( "recursive" );
        registerKeyword( "ref" );
        registerKeyword( "references" );
        registerKeyword( "referencing" );
        registerKeyword( "regexp_instr" );
        registerKeyword( "regexp_like" );
        registerKeyword( "regexp_substr" );
        registerKeyword( "regexp_replace" );
        registerKeyword( "regr_avgx" );
        registerKeyword( "regr_avgy" );
        registerKeyword( "regr_count" );
        registerKeyword( "regr_intercept" );
        registerKeyword( "regr_r2" );
        registerKeyword( "regr_slope" );
        registerKeyword( "regr_sxx" );
        registerKeyword( "regr_syy" );
        registerKeyword( "regr_sxy" );
        registerKeyword( "reject" );
        registerKeyword( "relative" );
        registerKeyword( "release" );
        registerKeyword( "rename" );
        registerKeyword( "reorganize" );
        registerKeyword( "repeat" );
        registerKeyword( "repeatable" );
        registerKeyword( "replace" );
        registerKeyword( "restore" );
        registerKeyword( "restrict" );
        registerKeyword( "result" );
        registerKeyword( "return" );
        registerKeyword( "returned_length" );
        registerKeyword( "returned_octet_length" );
        registerKeyword( "returns" );
        registerKeyword( "reverse" );
        registerKeyword( "revoke" );
        registerKeyword( "right" );
        registerKeyword( "role" );
        registerKeyword( "rollback" );
        registerKeyword( "rollup" );
        registerKeyword( "round" );
        registerKeyword( "routine" );
        registerKeyword( "row" );
        registerKeyword( "rowid" );
        registerKeyword( "rows" );
        registerKeyword( "rowtype" );
        registerKeyword( "row_number" );
        registerKeyword( "rpad" );
        registerKeyword( "rtrim" );
        registerKeyword( "savepoint" );
        registerKeyword( "scalar" );
        registerKeyword( "schema" );
        registerKeyword( "schemas" );
        registerKeyword( "scope" );
        registerKeyword( "script" );
        registerKeyword( "scroll" );
        registerKeyword( "search" );
        registerKeyword( "second" );
        registerKeyword( "seconds_between" );
        registerKeyword( "section" );
        registerKeyword( "secure" );
        registerKeyword( "security" );
        registerKeyword( "select" );
        registerKeyword( "selective" );
        registerKeyword( "self" );
        registerKeyword( "sensitive" );
        registerKeyword( "separator" );
        registerKeyword( "sequence" );
        registerKeyword( "serializable" );
        registerKeyword( "session" );
        registerKeyword( "session_user" );
        registerKeyword( "sessiontimezone" );
        registerKeyword( "set" );
        registerKeyword( "sets" );
        registerKeyword( "shortint" );
        registerKeyword( "shut" );
        registerKeyword( "sign" );
        registerKeyword( "similar" );
        registerKeyword( "simple" );
        registerKeyword( "sin" );
        registerKeyword( "sinh" );
        registerKeyword( "size" );
        registerKeyword( "skip" );
        registerKeyword( "smallint" );
        registerKeyword( "some" );
        registerKeyword( "soundex" );
        registerKeyword( "source" );
        registerKeyword( "space" );
        registerKeyword( "specific" );
        registerKeyword( "specifictype" );
        registerKeyword( "sql" );
        registerKeyword( "sqlexception" );
        registerKeyword( "sqlstate" );
        registerKeyword( "sqlwarning" );
        registerKeyword( "sql_bigint" );
        registerKeyword( "sql_bit" );
        registerKeyword( "sql_char" );
        registerKeyword( "sql_date" );
        registerKeyword( "sql_decimal" );
        registerKeyword( "sql_double" );
        registerKeyword( "sql_float" );
        registerKeyword( "sql_integer" );
        registerKeyword( "sql_longvarchar" );
        registerKeyword( "sql_numeric" );
        registerKeyword( "sql_preprocessor_script" );
        registerKeyword( "sql_real" );
        registerKeyword( "sql_smallint" );
        registerKeyword( "sql_timestamp" );
        registerKeyword( "sql_tinyint" );
        registerKeyword( "sql_type_date" );
        registerKeyword( "sql_type_timestamp" );
        registerKeyword( "sql_varchar" );
        registerKeyword( "sqrt" );
        registerKeyword( "start" );
        registerKeyword( "state" );
        registerKeyword( "statement" );
        registerKeyword( "static" );
        registerKeyword( "statistics" );
        registerKeyword( "stddev" );
        registerKeyword( "stddev_pop" );
        registerKeyword( "stddev_samp" );
        registerKeyword( "structure" );
        registerKeyword( "style" );
        registerKeyword( "st_isempty" );
        registerKeyword( "st_issimple" );
        registerKeyword( "st_distance" );
        registerKeyword( "st_dimension" );
        registerKeyword( "st_geometrytype" );
        registerKeyword( "st_boundary" );
        registerKeyword( "st_envelope" );
        registerKeyword( "st_convexhull" );
        registerKeyword( "st_equals" );
        registerKeyword( "st_disjoint" );
        registerKeyword( "st_intersects" );
        registerKeyword( "st_touches" );
        registerKeyword( "st_crosses" );
        registerKeyword( "st_within" );
        registerKeyword( "st_contains" );
        registerKeyword( "st_overlaps" );
        registerKeyword( "st_intersection" );
        registerKeyword( "st_difference" );
        registerKeyword( "st_union" );
        registerKeyword( "st_symdifference" );
        registerKeyword( "st_x" );
        registerKeyword( "st_y" );
        registerKeyword( "st_length" );
        registerKeyword( "st_isring" );
        registerKeyword( "st_startpoint" );
        registerKeyword( "st_endpoint" );
        registerKeyword( "st_centroid" );
        registerKeyword( "st_area" );
        registerKeyword( "st_buffer" );
        registerKeyword( "st_pointn" );
        registerKeyword( "st_exteriorring" );
        registerKeyword( "st_numpoints" );
        registerKeyword( "st_numgeometries" );
        registerKeyword( "st_numinteriorrings" );
        registerKeyword( "st_geometryn" );
        registerKeyword( "st_isclosed" );
        registerKeyword( "st_interiorringn" );
        registerKeyword( "st_setsrid" );
        registerKeyword( "st_transform" );
        registerKeyword( "st_force2d" );
        registerKeyword( "substr" );
        registerKeyword( "substring" );
        registerKeyword( "subtype" );
        registerKeyword( "sum" );
        registerKeyword( "symmetric" );
        registerKeyword( "sys_guid" );
        registerKeyword( "sysdate" );
        registerKeyword( "system" );
        registerKeyword( "system_user" );
        registerKeyword( "systimestamp" );
        registerKeyword( "table" );
        registerKeyword( "tables" );
        registerKeyword( "tablesample" );
        registerKeyword( "tan" );
        registerKeyword( "tanh" );
        registerKeyword( "tasks" );
        registerKeyword( "temporary" );
        registerKeyword( "text" );
        registerKeyword( "then" );
        registerKeyword( "ties" );
        registerKeyword( "time" );
        registerKeyword( "timestamp" );
        registerKeyword( "timezone_hour" );
        registerKeyword( "timezone_minute" );
        registerKeyword( "time_zone_behavior" );
        registerKeyword( "time_zone" );
        registerKeyword( "tinyint" );
        registerKeyword( "to" );
        registerKeyword( "to_char" );
        registerKeyword( "to_date" );
        registerKeyword( "to_dsinterval" );
        registerKeyword( "to_number" );
        registerKeyword( "to_timestamp" );
        registerKeyword( "to_yminterval" );
        registerKeyword( "trailing" );
        registerKeyword( "transaction" );
        registerKeyword( "transform" );
        registerKeyword( "transforms" );
        registerKeyword( "translate" );
        registerKeyword( "translation" );
        registerKeyword( "treat" );
        registerKeyword( "trigger" );
        registerKeyword( "trim" );
        registerKeyword( "true" );
        registerKeyword( "trunc" );
        registerKeyword( "truncate" );
        registerKeyword( "type" );
        registerKeyword( "ucase" );
        registerKeyword( "unbounded" );
        registerKeyword( "uncommitted" );
        registerKeyword( "under" );
        registerKeyword( "undo" );
        registerKeyword( "unicode" );
        registerKeyword( "unicodechr" );
        registerKeyword( "union" );
        registerKeyword( "unique" );
        registerKeyword( "unknown" );
        registerKeyword( "unlimited" );
        registerKeyword( "unlink" );
        registerKeyword( "unnest" );
        registerKeyword( "until" );
        registerKeyword( "update" );
        registerKeyword( "upper" );
        registerKeyword( "usage" );
        registerKeyword( "user" );
        registerKeyword( "using" );
        registerKeyword( "utf8" );
        registerKeyword( "value" );
        registerKeyword( "values" );
        registerKeyword( "varchar" );
        registerKeyword( "nvarchar" );
        registerKeyword( "varchar2" );
        registerKeyword( "nvarchar2" );
        registerKeyword( "variance" );
        registerKeyword( "varray" );
        registerKeyword( "varying" );
        registerKeyword( "var_pop" );
        registerKeyword( "var_samp" );
        registerKeyword( "verify" );
        registerKeyword( "view" );
        registerKeyword( "when" );
        registerKeyword( "week" );
        registerKeyword( "whenever" );
        registerKeyword( "where" );
        registerKeyword( "while" );
        registerKeyword( "window" );
        registerKeyword( "with" );
        registerKeyword( "within" );
        registerKeyword( "without" );
        registerKeyword( "work" );
        registerKeyword( "write" );
        registerKeyword( "year" );
        registerKeyword( "years_between" );
        registerKeyword( "yes" );
        registerKeyword( "zeroifnull" );
        registerKeyword( "zone" );
    }

   

	public String getAddColumnString() {
		return "add";
	}

    @Override
	public String getCascadeConstraintsString() {
		return " cascade";
	}

    @Override
	public boolean dropConstraints() {
		return true;
	}

    @Override
	public boolean supportsSequences() {
		return false;
	}  

    @Override
	public boolean supportsLimit() {
		return true;
	}

    @Override
	public String getLimitString(String sql, boolean hasOffset) {
		return new StringBuilder( sql.length()+20 )
				.append( sql )
				.append( hasOffset ? " limit ? offset ?" : " limit ?" )
				.toString();
	}

    @Override
	public boolean bindLimitParametersInReverseOrder() {
		return false;
	}

    @Override
    public IdentityColumnSupport getIdentityColumnSupport(){
    	return new ExasolIdentityColumnSupport();
    }
    

	@Override
	public String getNoColumnsInsertString() {
		return "default values";
	}

	@Override
	public String getCaseInsensitiveLike(){
		return "ilike";
	}

	@Override
	public boolean supportsCaseInsensitiveLike() {
		return true;
	}

	@Override
	public Class getNativeIdentifierGeneratorClass() {
		return SequenceGenerator.class;
	}

	@Override
	public boolean supportsOuterJoinForUpdate() {
		return false;
	}
	
	@Override
	public boolean useInputStreamToInsertBlob() {
		return false;
	}

	@Override
	public boolean supportsUnionAll() {
		return true;
	}

	@Override
	public boolean supportsCommentOn() {
		return true;
	}

	public boolean supportsTemporaryTables() {
        return false;
    }

	@Override
	public boolean supportsCurrentTimestampSelection() {
		return true;
	}

	@Override
	public boolean isCurrentTimestampSelectStringCallable() {
		return false;
	}

	@Override
	public String getCurrentTimestampSelectString() {
		return "select now()";
	}

	@Override
	public boolean supportsTupleDistinctCounts() {
		return false;
	}

	@Override
	public String toBooleanValueString(boolean bool) {
		return bool ? "true" : "false";
	}

	public ViolatedConstraintNameExtracter getViolatedConstraintNameExtracter() {
		return EXTRACTER;
	}

	private static ViolatedConstraintNameExtracter EXTRACTER = new TemplatedViolatedConstraintNameExtracter() {
		public String doExtractConstraintName(SQLException sqle) {
			try {
				int sqlState = Integer.valueOf( JdbcExceptionHelper.extractSqlState( sqle )).intValue();
				switch (sqlState) {
					// CHECK VIOLATION
					case 23514: return extractUsingTemplate("violates check constraint \"","\"", sqle.getMessage());
					// UNIQUE VIOLATION
					case 23505: return extractUsingTemplate("violates unique constraint \"","\"", sqle.getMessage());
					// FOREIGN KEY VIOLATION
					case 23503: return extractUsingTemplate("violates foreign key constraint \"","\"", sqle.getMessage());
					// NOT NULL VIOLATION
					case 23502: return extractUsingTemplate("null value in column \"","\" violates not-null constraint", sqle.getMessage());
					// TODO: RESTRICT VIOLATION
					case 23001: return null;
					// ALL OTHER
					default: return null;
				}
			} catch (NumberFormatException nfe) {
				return null;
			}
		}
	};
	
	@Override
	public SQLExceptionConversionDelegate buildSQLExceptionConversionDelegate() {
		return new SQLExceptionConversionDelegate() {
			public JDBCException convert(SQLException sqlException, String message, String sql) {
				final String sqlState = JdbcExceptionHelper.extractSqlState( sqlException );

				if ( "40P01".equals( sqlState ) ) { // DEADLOCK DETECTED
					return new LockAcquisitionException( message, sqlException, sql );
				}

				if ( "55P03".equals( sqlState ) ) { // LOCK NOT AVAILABLE
					return new PessimisticLockException( message, sqlException, sql );
				}

				// returning null allows other delegates to operate
				return null;
			}
		};
	}
	
	public int registerResultSetOutParameter(CallableStatement statement, int col) throws SQLException {
		// Register the type of the out param - PostgreSQL uses Types.OTHER
		statement.registerOutParameter(col++, Types.OTHER);
		return col;
	}

	public ResultSet getResultSet(CallableStatement ps) throws SQLException {
		ps.execute();
		return (ResultSet) ps.getObject(1);
	}


	public boolean supportsPooledSequences() {
		return true;
	}

    public boolean supportsEmptyInList() {
		return false;
	}

	@Override
	public boolean supportsExpectedLobUsagePattern() {
		return true;
	}

	@Override
	public boolean supportsLobValueChangePropogation() {
		return false;
	}

	@Override
	public boolean supportsUnboundedLobLocatorMaterialization() {
		return false;
	}

	// locking support
    @Override
	public String getForUpdateString() {
		return "";
	}

	public String getWriteLockString(int timeout) {
		if ( timeout == LockOptions.NO_WAIT )
			return " for update nowait";
		else
			return " for update";
	}

	public String getReadLockString(int timeout) {
		if ( timeout == LockOptions.NO_WAIT )
			return " for share nowait";
		else
			return " for share";
	}

	public boolean supportsRowValueConstructorSyntax() {
		return true;
	}
	

    public Boolean performTemporaryTableDDLInIsolation() {
        return Boolean.TRUE;
    }

    public boolean supportsCascadeDelete() {
        return false;
    }
}
