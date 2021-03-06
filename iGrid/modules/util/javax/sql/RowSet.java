/*
 * @(#)RowSet.java	1.1 99/05/11
 * 
 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 * 
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 * 
 */

package javax.sql;

import java.sql.*;
import java.io.*;
import java.math.*;
import java.util.*;

/**
 * 
 * <P>The RowSet interface adds support to the JDBC API for the
 * JavaBeans(TM) component model.  A rowset can be used as a JavaBean in
 * a visual Bean development environment. A RowSet can be created and
 * configured at design time and executed at runtime.  The RowSet
 * interface provides a set of JavaBeans properties that allow a RowSet
 * instance to be configured to connect to a JDBC data source and read
 * some data from the data source.  A group of setXXX() methods
 * provide a way to pass input parameters to a rowset.  The RowSet
 * interface supports JavaBeans events, allowing other components in an
 * application to be notified when an important event on a rowset occurs,
 * such as a change in its value.
 * 
 * <P>The RowSet interface is unique in that it is intended to be
 * implemented using the rest of the JDBC(TM) API.  In other words, a
 * RowSet implementation is a layer of software that executes "on top"
 * of a JDBC driver.  Implementations of the RowSet interface can
 * be provided by anyone, including JDBC driver vendors who want to
 * provide a RowSet implementation as part of their JDBC products. 
 * 
 * <P>Rowsets are easy to use.  The RowSet interface extends the standard
 * java.sql.ResultSet interface.  The RowSetMetaData interface extends
 * the java.sql.ResultSetMetaData interface. Thus, developers familiar
 * with the JDBC API will have to learn a minimal number of new APIs to
 * use rowsets.  In addition, third-party software tools that work with
 * JDBC ResultSets will also easily be made to work with rowsets.
 * 
 */

public interface RowSet extends ResultSet {
  
  //-----------------------------------------------------------------------
  // Properties 
  //-----------------------------------------------------------------------

  //-----------------------------------------------------------------------
  // The following properties may be used to create a Connection.
  //-----------------------------------------------------------------------

  /** 
   * Get the url used to create a JDBC connection. The default value 
   * is null.
   *
   * @return a string url
   * @exception SQLException if a database-access error occurs.
   */
  String getUrl() throws SQLException;

  /**
   * Set the url used to create a connection.
   *
   * Setting this property is optional.  If a url is used, a JDBC driver
   * that accepts the url must be loaded by the application before the
   * rowset is used to connect to a database.  The rowset will use the url
   * internally to create a database connection when reading or writing
   * data.  Either a url or a data source name is used to create a
   * connection, whichever was specified most recently.
   * 
   * @param url a string value, may be null
   * @exception SQLException if a database-access error occurs.
   */
  void setUrl(String url) throws SQLException;

  /**
   * The JNDI name that identifies a JDBC data source.  Users should set
   * either the url or data source name properties.  The most recent 
   * property set is used to get a connection.
   *
   * @return a data source name
   * @exception SQLException if a database-access error occurs.
   */
  String getDataSourceName();

  /**
   * Set the data source name.
   *
   * @param name a data source name
   * @exception SQLException if a database-access error occurs.
   */
  void setDataSourceName(String name) throws SQLException;

  /** 
   * The username used to create a database connection.  The username
   * property is set at runtime before calling execute().  It is 
   * not usually part of the serialized state of a rowset object.
   *
   * @return a user name
   * @exception SQLException if a database-access error occurs.
   */
  String getUsername();

  /**
   * Set the user name.
   *
   * @param name a user name
   * @exception SQLException if a database-access error occurs.
   */
  void setUsername(String name) throws SQLException;

  /** 
   * The password used to create a database connection.  The password
   * property is set at runtime before calling execute().  It is 
   * not usually part of the serialized state of a rowset object.
   *
   * @return a password
   * @exception SQLException if a database-access error occurs.
   */
  String getPassword();

  /**
   * Set the password.
   *
   * @param password the password string
   * @exception SQLException if a database-access error occurs.
   */
  void setPassword(String password) throws SQLException;

  /** 
   * The transaction isolation property contains the JDBC transaction
   * isolation level used.
   *
   * @return the transaction isolation level
   * @exception SQLException if a database-access error occurs.
   */
  int getTransactionIsolation();

  /**
   * Set the transaction isolation.
   *
   * @param level the transaction isolation level
   * @exception SQLException if a database-access error occurs.
   */
  void setTransactionIsolation(int level) throws SQLException;

  /**
   * Get the type-map object associated with this rowset.
   * By default, the map returned is empty.
   *
   * @return a map object
   * @exception SQLException if a database-access error occurs.
   */
  java.util.Map getTypeMap() throws SQLException;

  /**
   * Install a type-map object as the default type-map for
   * this rowset.
   *
   * @param map a map object
   * @exception SQLException if a database-access error occurs.
   */
  void setTypeMap(java.util.Map map) throws SQLException;

  //-----------------------------------------------------------------------
  // The following properties may be used to create a Statement.
  //-----------------------------------------------------------------------

  /** 
   * Get the rowset's command property.
   *
   * The command property contains a command string that can be executed to
   * fill the rowset with data.  The default value is null.
   *
   * @return the command string, may be null
   * @exception SQLException if a database-access error occurs.  
   */
  String getCommand();

  /**
   * Set the rowset's command property.
   *
   * This property is optional.  The command property may not be needed
   * when a rowset is produced by a data source that doesn't support
   * commands, such as a spreadsheet. 
   *
   * @param cmd a command string, may be null
   * @exception SQLException if a database-access error occurs.
   */
  void setCommand(String cmd) throws SQLException;

  /** 
   * A rowset may be read-only.  Attempts to update a
   * read-only rowset will result in an SQLException being thrown. 
   * Rowsets are updateable, by default, if updates are possible.
   *
   * @return true if updatable, false otherwise
   * @exception SQLException if a database-access error occurs.
   */
  boolean isReadOnly();

  /**
   * Set the read-onlyness of the rowset
   *
   * @param value true if read-only, false otherwise
   * @exception SQLException if a database-access error occurs.
   */
  void setReadOnly(boolean value) throws SQLException;

  /**
   * The maxFieldSize limit (in bytes) is the maximum amount of data
   * returned for any column value; it only applies to BINARY,
   * VARBINARY, LONGVARBINARY, CHAR, VARCHAR, and LONGVARCHAR
   * columns.  If the limit is exceeded, the excess data is silently
   * discarded.
   *
   * @return the current max column size limit; zero means unlimited 
   * @exception SQLException if a database-access error occurs.
   */
  int getMaxFieldSize() throws SQLException;
    
  /**
   * The maxFieldSize limit (in bytes) is set to limit the size of
   * data that can be returned for any column value; it only applies
   * to BINARY, VARBINARY, LONGVARBINARY, CHAR, VARCHAR, and
   * LONGVARCHAR fields.  If the limit is exceeded, the excess data
   * is silently discarded. For maximum portability use values
   * greater than 256.
   *
   * @param max the new max column size limit; zero means unlimited 
   * @exception SQLException if a database-access error occurs.
   */
  void setMaxFieldSize(int max) throws SQLException;

  /**
   * The maxRows limit is the maximum number of rows that a
   * RowSet can contain.  If the limit is exceeded, the excess
   * rows are silently dropped.
   *
   * @return the current max row limit; zero means unlimited
   * @exception SQLException if a database-access error occurs.
   */
  int getMaxRows() throws SQLException;

  /**
   * The maxRows limit is set to limit the number of rows that any
   * RowSet can contain.  If the limit is exceeded, the excess
   * rows are silently dropped.
   *
   * @param max the new max rows limit; zero means unlimited 
   * @exception SQLException if a database-access error occurs.
   */
  void setMaxRows(int max) throws SQLException;

  /**
   * If escape scanning is on (the default), the driver will do
   * escape substitution before sending the SQL to the database.
   *
   * @return true if enabled; false if disabled
   * @exception SQLException if a database-access error occurs.
   */
  boolean getEscapeProcessing() throws SQLException;

  /**
   * If escape scanning is on (the default), the driver will do
   * escape substitution before sending the SQL to the database.
   *
   * @param enable true to enable; false to disable
   * @exception SQLException if a database-access error occurs.
   */
  void setEscapeProcessing(boolean enable) throws SQLException;

  /**
   * The queryTimeout limit is the number of seconds the driver will
   * wait for a Statement to execute. If the limit is exceeded, a
   * SQLException is thrown.
   *
   * @return the current query timeout limit in seconds; zero means 
   * unlimited 
   * @exception SQLException if a database-access error occurs.
   */
  int getQueryTimeout() throws SQLException;

  /**
   * The queryTimeout limit is the number of seconds the driver will
   * wait for a Statement to execute. If the limit is exceeded, a
   * SQLException is thrown.
   *
   * @param seconds the new query timeout limit in seconds; zero means 
   * unlimited 
   * @exception SQLException if a database-access error occurs.
   */
  void setQueryTimeout(int seconds) throws SQLException;

  /**
   * Set the rowset type.
   *
   * @param type a value from ResultSet.TYPE_XXX
   * @exception SQLException if a database-access error occurs.
   */
  void setType(int type) throws SQLException;

  /**
   * Set the rowset concurrency.
   *
   * @param concurrency a value from ResultSet.CONCUR_XXX
   * @exception SQLException if a database-access error occurs.
   */
  void setConcurrency(int concurrency) throws SQLException;
  
  //-----------------------------------------------------------------------
  // Parameters
  //-----------------------------------------------------------------------

  /** 
   * The setXXX() methods are used to set any input parameters needed by
   * command (above).  Parameters are set at runtime, as opposed to design 
   * time.
   */

  /**
   * Set a parameter to SQL NULL.
   *
   * <P><B>Note:</B> You must specify the parameter's SQL type.
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param sqlType SQL type code defined by java.sql.Types
   * @exception SQLException if a database-access error occurs.
   */
  void setNull(int parameterIndex, int sqlType) throws SQLException;

  /**
   * JDBC 2.0
   *
   * Set a parameter to SQL NULL.  This version of setNull should
   * be used for user-named types and REF type parameters.  Examples
   * of user-named types include: STRUCT, DISTINCT, JAVA_OBJECT, and 
   * named array types.
   *
   * <P><B>Note:</B> To be portable, applications must give the
   * SQL type code and the fully qualified SQL type name when specifying
   * a NULL user-named or REF parameter.  In the case of a user-named type 
   * the name is the type name of the parameter itself.  For a REF 
   * parameter the name is the type name of the referenced type.  If 
   * a JDBC driver does not need the type code or type name information, 
   * it may ignore it.     
   *
   * Although it is intended for user-named and Ref parameters,
   * this method may be used to set a null parameter of any JDBC type.
   * If the parameter does not have a user-named or REF type then the
   * typeName is ignored.
   *
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param sqlType a value from java.sql.Types
   * @param typeName the fully qualified name of a SQL user-named type,
   *  ignored if the parameter is not a user-named type or REF 
   * @exception SQLException if a database-access error occurs.
   */
  void setNull (int paramIndex, int sqlType, String typeName) 
    throws SQLException;

  /**
   * Set a parameter to a Java boolean value.   
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setBoolean(int parameterIndex, boolean x) throws SQLException;

  /**
   * Set a parameter to a Java byte value.  
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setByte(int parameterIndex, byte x) throws SQLException;
  
  /**
   * Set a parameter to a Java short value.   
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setShort(int parameterIndex, short x) throws SQLException;

  /**
   * Set a parameter to a Java int value.   
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setInt(int parameterIndex, int x) throws SQLException;

  /**
   * Set a parameter to a Java long value.  
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setLong(int parameterIndex, long x) throws SQLException;

  /**
   * Set a parameter to a Java float value.  The driver converts this
   * to a SQL FLOAT value when it sends it to the database.
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setFloat(int parameterIndex, float x) throws SQLException;

  /**
   * Set a parameter to a Java double value. 
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setDouble(int parameterIndex, double x) throws SQLException;
  
  /**
   * Set a parameter to a java.lang.BigDecimal value.  
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException;

  /**
   * Set a parameter to a Java String value.   
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setString(int parameterIndex, String x) throws SQLException;

  /**
   * Set a parameter to a Java array of bytes.  
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value 
   * @exception SQLException if a database-access error occurs.
   */
  void setBytes(int parameterIndex, byte x[]) throws SQLException;

  /**
   * Set a parameter to a java.sql.Date value.   
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setDate(int parameterIndex, java.sql.Date x) throws SQLException;

  /**
   * Set a parameter to a java.sql.Time value.   
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setTime(int parameterIndex, java.sql.Time x) throws SQLException;

  /**
   * Set a parameter to a java.sql.Timestamp value.   
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value 
   * @exception SQLException if a database-access error occurs.
   */
  void setTimestamp(int parameterIndex, java.sql.Timestamp x)
    throws SQLException;

  /**
   * When a very large ASCII value is input to a LONGVARCHAR
   * parameter, it may be more practical to send it via a
   * java.io.InputStream. JDBC will read the data from the stream
   * as needed, until it reaches end-of-file.   
   * 
   * <P><B>Note:</B> This stream object can either be a standard
   * Java stream object or your own subclass that implements the
   * standard interface.
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the java input stream which contains the ASCII parameter value
   * @param length the number of bytes in the stream 
   * @exception SQLException if a database-access error occurs.
   */
  void setAsciiStream(int parameterIndex, java.io.InputStream x, int length)
    throws SQLException;
  
  /**
   * When a very large binary value is input to a LONGVARBINARY
   * parameter, it may be more practical to send it via a
   * java.io.InputStream. JDBC will read the data from the stream
   * as needed, until it reaches end-of-file.
   * 
   * <P><B>Note:</B> This stream object can either be a standard
   * Java stream object or your own subclass that implements the
   * standard interface.
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the java input stream which contains the binary parameter value
   * @param length the number of bytes in the stream 
   * @exception SQLException if a database-access error occurs.
   */
  void setBinaryStream(int parameterIndex, java.io.InputStream x, 
		       int length) throws SQLException;

  /**
   * When a very large UNICODE value is input to a LONGVARCHAR
   * parameter, it may be more practical to send it via a
   * java.io.Reader. JDBC will read the data from the stream
   * as needed, until it reaches end-of-file.  
   * 
   * <P><B>Note:</B> This stream object can either be a standard
   * Java stream object or your own subclass that implements the
   * standard interface.
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the java reader which contains the UNICODE data
   * @param length the number of characters in the stream 
   * @exception SQLException if a database-access error occurs.
   */
  void setCharacterStream(int parameterIndex,
       			  Reader reader,
			  int length) throws SQLException;
  
  /**
   * <p>Set the value of a parameter using an object; use the
   * java.lang equivalent objects for integral values.
   *
   * <p>The given Java object will be converted to the targetSqlType
   * before being sent to the database.
   *
   * If the object is of a class implementing SQLData,
   * the rowset should call its method writeSQL() to write it 
   * to the SQL data stream.
   * else
   * If the object is of a class implementing Ref, Blob, Clob, Struct, 
   * or Array then pass it to the database as a value of the 
   * corresponding SQL type.
   *
   * <p>Note that this method may be used to pass datatabase-
   * specific abstract data types. 
   *
   * @param parameterIndex The first parameter is 1, the second is 2, ...
   * @param x The object containing the input parameter value
   * @param targetSqlType The SQL type (as defined in java.sql.Types) to be 
   * sent to the database. The scale argument may further qualify this type.
   * @param scale For java.sql.Types.DECIMAL or java.sql.Types.NUMERIC types
   *          this is the number of digits after the decimal.  For all other
   *          types this value will be ignored,
   * @exception SQLException if a database-access error occurs.
   * @see Types 
   */
  void setObject(int parameterIndex, Object x, int targetSqlType, int scale)
            throws SQLException;

  /**
   * This method is like setObject above, but the scale used is the scale
   * of the second parameter.  Scalar values have a scale of zero.  Literal
   * values have the scale present in the literal.  While it is supported, it 
   * is not recommended that this method not be called with floating point 
   * input values.
   *
   * @exception SQLException if a database-access error occurs.
   */
  void setObject(int parameterIndex, Object x, 
		 int targetSqlType) throws SQLException;
  
  /**
   * <p>Set the value of a parameter using an object; use the
   * java.lang equivalent objects for integral values.
   *
   * <p>The JDBC specification specifies a standard mapping from
   * Java Object types to SQL types.  The given argument java object
   * will be converted to the corresponding SQL type before being
   * sent to the database.
   *
   * <p>Note that this method may be used to pass datatabase
   * specific abstract data types, by using a Driver specific Java
   * type.
   *
   * If the object is of a class implementing SQLData,
   * the rowset should call its method writeSQL() to write it 
   * to the SQL data stream.
   * else
   * If the object is of a class implementing Ref, Blob, Clob, Struct, 
   * or Array then pass it to the database as a value of the 
   * corresponding SQL type.
   *
   * Raise an exception if there is an ambiguity, for example, if the
   * object is of a class implementing more than one of those interfaces.
   *
   * @param parameterIndex The first parameter is 1, the second is 2, ...
   * @param x The object containing the input parameter value 
   * @exception SQLException if a database-access error occurs.
   */
  void setObject(int parameterIndex, Object x) throws SQLException;

  /**
   * Set a REF(&lt;structured-type&gt;) parameter.
   *
   * @param i the first parameter is 1, the second is 2, ...
   * @param x an object representing data of an SQL REF Type
   */
  void setRef (int i, Ref x) throws SQLException;

  /** 
   * Set a BLOB parameter.
   *
   * @param i the first parameter is 1, the second is 2, ...
   * @param x an object representing a BLOB
   */
  void setBlob (int i, Blob x) throws SQLException;

  /** 
   * Set a CLOB parameter.
   *
   * @param i the first parameter is 1, the second is 2, ...
   * @param x an object representing a CLOB
   */
  void setClob (int i, Clob x) throws SQLException;
  
  /** 
   * Set an Array parameter.
   *
   * @param i the first parameter is 1, the second is 2, ...
   * @param x an object representing an SQL array
   */
  void setArray (int i, Array x) throws SQLException;

  /**
   * Set a parameter to a java.sql.Date value.  The driver converts this
   * to a SQL DATE value when it sends it to the database.
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setDate(int parameterIndex, java.sql.Date x, Calendar cal)
    throws SQLException;

  /**
   * Set a parameter to a java.sql.Time value.  The driver converts this
   * to a SQL TIME value when it sends it to the database.
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value
   * @exception SQLException if a database-access error occurs.
   */
  void setTime(int parameterIndex, java.sql.Time x, Calendar cal) 
    throws SQLException;

  /**
   * Set a parameter to a java.sql.Timestamp value.  The driver
   * converts this to a SQL TIMESTAMP value when it sends it to the
   * database.
   *
   * @param parameterIndex the first parameter is 1, the second is 2, ...
   * @param x the parameter value 
   * @exception SQLException if a database-access error occurs.
   */
  void setTimestamp(int parameterIndex, java.sql.Timestamp x, Calendar cal)
    throws SQLException;

  /**
   * <P>In general, parameter values remain in force for repeated use of a
   * RowSet. Setting a parameter value automatically clears its
   * previous value.  However, in some cases it is useful to immediately
   * release the resources used by the current parameter values; this can
   * be done by calling clearParameters.
   *
   * @exception SQLException if a database-access error occurs.
   */
  void clearParameters() throws SQLException;

  //---------------------------------------------------------------------
  // Reading and writing data
  //---------------------------------------------------------------------

  /**
   * Fills the rowset with data.  
   *
   * Execute() may use the following properties: url, data source name, 
   * user name, password, transaction isolation, and type map to create a 
   * connection for reading data.
   * 
   * Execute may use the following properties to create a statement
   * to execute a command: command, read only, maximum field size, 
   * maximum rows, escape processing, and query timeout.
   *
   * If the required properties have not been set, an exception is 
   * thrown.  If successful, the current contents of the rowset are 
   * discarded and the rowset's metadata is also (re)set.  If there are 
   * outstanding updates, they are ignored.   
   *
   * @exception SQLException if a database-access error occurs.
   */
  void execute() throws SQLException;

  //--------------------------------------------------------------------
  // Events
  //--------------------------------------------------------------------

  /**
   * RowSet listener registration.  Listeners are notified
   * when an event occurs.
   *
   * @param listener an event listener
   */
  void addRowSetListener(RowSetListener listener);

  /**
   * RowSet listener deregistration.  
   *
   * @param listener an event listener
   */
  void removeRowSetListener(RowSetListener listener);
 
}









