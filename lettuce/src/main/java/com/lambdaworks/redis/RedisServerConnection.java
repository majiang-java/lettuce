package com.lambdaworks.redis;

import java.util.Date;
import java.util.List;

/**
 * Synchronous executed commands for Server Control.
 * 
 * @param <K> Key type.
 * @param <V> Value type.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 17.05.14 21:32
 */
public interface RedisServerConnection<K, V> {
    /**
     * Asynchronously rewrite the append-only file.
     * 
     * @return String simple-string-reply always `OK`.
     */
    String bgrewriteaof();

    /**
     * Asynchronously save the dataset to disk.
     * 
     * @return String simple-string-reply
     */
    String bgsave();

    /**
     * Get the current connection name.
     * 
     * @return K bulk-string-reply The connection name, or a null bulk reply if no name is set.
     */
    K clientGetname();

    /**
     * Set the current connection name.
     * 
     * @param name
     * @return simple-string-reply `OK` if the connection name was successfully set.
     */
    String clientSetname(K name);

    /**
     * Kill the connection of a client identified by ip:port.
     * 
     * @param addr ip:port
     * @return String simple-string-reply `OK` if the connection exists and has been closed
     */
    String clientKill(String addr);

    /**
     * Stop processing commands from clients for some time.
     * 
     * @param timeout
     * @return String simple-string-reply The command returns OK or an error if the timeout is invalid.
     */
    String clientPause(long timeout);

    /**
     * Get the list of client connections.
     * 
     * @return String bulk-string-reply a unique string, formatted as follows: One client connection per line (separated by LF),
     *         each line is composed of a succession of property=value fields separated by a space character.
     */
    String clientList();

    /**
     * Get the value of a configuration parameter.
     * 
     * @param parameter
     * @return List<String> bulk-string-reply
     */
    List<String> configGet(String parameter);

    /**
     * Reset the stats returned by INFO.
     * 
     * @return String simple-string-reply always `OK`.
     */
    String configResetstat();

    /**
     * Rewrite the configuration file with the in memory configuration.
     * 
     * @return String simple-string-reply `OK` when the configuration was rewritten properly. Otherwise an error is returned.
     */
    String configRewrite();

    /**
     * Set a configuration parameter to the given value.
     * 
     * @param parameter
     * @param value
     * @return String simple-string-reply: `OK` when the configuration was set properly. Otherwise an error is returned.
     */
    String configSet(String parameter, String value);

    /**
     * Return the number of keys in the selected database.
     * 
     * @return Long integer-reply
     */
    Long dbsize();

    /**
     * Get debugging information about a key.
     * 
     * @param key the key
     * @return String simple-string-reply
     */
    String debugObject(K key);

    /**
     * Make the server crash.
     */
    void debugSegfault();

    /**
     * Remove all keys from all databases.
     * 
     * @return String simple-string-reply
     */
    String flushall();

    /**
     * Remove all keys from the current database.
     * 
     * @return String simple-string-reply
     */
    String flushdb();

    /**
     * Get information and statistics about the server.
     * 
     * @return String bulk-string-reply as a collection of text lines.
     */
    String info();

    /**
     * Get information and statistics about the server.
     * 
     * @param section the section type: string
     * @return String bulk-string-reply as a collection of text lines.
     */
    String info(String section);

    /**
     * Get the UNIX time stamp of the last successful save to disk.
     * 
     * @return Date integer-reply an UNIX time stamp.
     */
    Date lastsave();

    /**
     * Synchronously save the dataset to disk.
     * 
     * @return String simple-string-reply The commands returns OK on success.
     */
    String save();

    /**
     * Synchronously save the dataset to disk and then shut down the server.
     * 
     * @param save
     */
    void shutdown(boolean save);

    /**
     * Make the server a slave of another instance, or promote it as master.
     * 
     * @param host the host type: string
     * @param port the port type: string
     * @return String simple-string-reply
     */
    String slaveof(String host, int port);

    /**
     * Promote server as master.
     * 
     * @return String simple-string-reply
     */
    String slaveofNoOne();

    /**
     * Read the slow log.
     * 
     * @return List<Object> deeply nested multi bulk replies
     */
    List<Object> slowlogGet();

    /**
     * Read the slow log.
     * 
     * @param count the count
     * @return List<Object> deeply nested multi bulk replies
     */
    List<Object> slowlogGet(int count);

    /**
     * Obtaining the current length of the slow log.
     * 
     * @return Long length of the slow log.
     */
    Long slowlogLen();

    /**
     * Resetting the slow log.
     * 
     * @return String simple-string-reply The commands returns OK on success.
     */
    String slowlogReset();

    /**
     * Internal command used for replication.
     * 
     * @return
     */
    String sync();

    /**
     * Return the current server time.
     * 
     * @return List<V> array-reply specifically:
     * 
     *         A multi bulk reply containing two elements:
     * 
     *         unix time in seconds. microseconds.
     */
    List<V> time();
}