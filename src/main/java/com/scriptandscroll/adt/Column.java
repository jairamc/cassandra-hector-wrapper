package com.scriptandscroll.adt;

import com.scriptandscroll.exceptions.InvalidValueException;
import me.prettyprint.hector.api.beans.HColumn;

/**
 *
 * @author Courtney
 */
public class Column implements Savable {

	private boolean isChanged;
	private String name;
	private String value;

	/**
	 * Creates a Column from a Hector column
	 * @param hectorCol 
	 */
	public Column(HColumn<String, String> hectorCol) {
		if (hectorCol == null) {
			return;
		}
		name = hectorCol.getName();
		value = hectorCol.getValue();
	}

	/**
	 * Create a column who's value can be set later or if left unset 
	 * a column is created with an empty string as its value
	 * @param name the column name
	 * @throws  InvalidValueException When the name of the column is either null or empty
	 */
	public Column(String name) {
		this(name, null);
	}

	/**
	 * Create a column without specifying a timestamp and allow one to be generated
	 * @param name the column name
	 * @param value the value for the column
	 * @throws  InvalidValueException When the name of the column is either null or empty
	 */
	public Column(String name, String value) {
		if (name == null || name.isEmpty()) {
			throw new InvalidValueException("The name of a column cannot be null or empty!");
		}
		this.name = name;
		this.value = value;
	}

	/**
	 * @return The name of this column
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The value for this column
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param name Set the column's name
	 */
	public void setName(String name) {
		this.name = name;
		setChanged(true);
	}

	/**
	 * @param value The column's value
	 */
	public void setValue(String value) {
		this.value = value;
		setChanged(true);
	}

	/**
	 * @return true if any property of this object has been modified
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * Mark this object as being changed or unchanged (modified/unmodified)
	 * @param changed If true then all changes will be automatically committed to
	 * Cassandra, If false then even if changes were made they will not be written to Cassandra
	 */
	public void setChanged(boolean changed) {
		isChanged = changed;
	}
}
