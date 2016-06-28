package com.stpl.app.util;

/**
 * The Class HelperDTO.
 */
public class HelperDTO {

	/** The identifier. */
	private int identifier;

	/** The description. */
	private String description = HelperUtils.EMPTY;

	/**
	 * The Constructor.
	 *
	 * @param identifier
	 *            the identifier
	 * @param description
	 *            the description
	 */
	public HelperDTO(final int identifier, final String description) {
		this.identifier = identifier;
		this.description = description;
	}

	/**
	 * The Constructor.
	 *
	 * @param description
	 *            the description
	 */
	public HelperDTO(final String description) {
		this.identifier = 0;
		this.description = description;
	}

	/**
	 * (non-Javadoc).
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return description;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return identifier;
	}

	/**
	 * Sets the id.
	 *
	 * @param identifier
	 *            the id
	 */
	public void setId(final int identifier) {
		this.identifier = identifier;
	}

	/**
	 * Gets the identifier.
	 *
	 * @return the identifier
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier.
	 *
	 * @param identifier
	 *            the identifier
	 */
	public void setIdentifier(final int identifier) {
		this.identifier = identifier;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

}
