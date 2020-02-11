package com.swivel.repository.exception

/**
 * @author Yohan Dushmantha
 * @class DataSourceNotHandledException
 *
 * this exception can be thrown when not handling requested datasource inside relevent repository
 * developer should implement requested datasource when he get this exception
 */
class DataSourceNotHandledException : Exception() {

    override val message: String?
        get() = "Data Source Not Handled inside Repository Class"
}