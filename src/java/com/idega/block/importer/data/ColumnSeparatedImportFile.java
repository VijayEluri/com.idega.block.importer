package com.idega.block.importer.data;

import java.io.File;
import java.util.Collection;

/**
 * @author <a href="mailto:eiki@idega.is">Eirikur S. Hrafnsson</a>
 *
 */
public class ColumnSeparatedImportFile extends GenericImportFile {

	/**
	 * Constructor for ColumnSeparatedImportFile.
	 * @param file
	 */
	public ColumnSeparatedImportFile(File file) {
		super(file);
		this.setAddNewLineAfterRecord(false);
		this.setRecordDilimiter("\n");
		this.setValueSeparator(";");
	}



}