/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesProjectionDTO.
 *
 * @author maheshj
 */
public class SalesProjectionDTO {

    /** The check. */
    private boolean check;

    /** The level. */
    private String level = StringUtils.EMPTY;
    
    /** The actualsales. */
    private String actualsales = StringUtils.EMPTY;
    
    /** The projectedsales. */
    private Date projectedsales = new Date();
    
    /** The actualunits. */
    private Double actualunits = 0.0;
    
    /** The projectedunits. */
    private String projectedunits = StringUtils.EMPTY;
    
    /** The actualsales1. */
    private String actualsales1 = StringUtils.EMPTY;
    
    /** The projectedsales1. */
    private String projectedsales1 = StringUtils.EMPTY;
    
    /** The actualunits1. */
    private String actualunits1 = StringUtils.EMPTY;

    /** The projectedunits1. */
    private String projectedunits1 = StringUtils.EMPTY;
    
    /** The actualsales2. */
    private String actualsales2 = StringUtils.EMPTY;
    
    /** The projectedsales2. */
    private String projectedsales2 = StringUtils.EMPTY;
    
    /** The actualunits2. */
    private String actualunits2 = StringUtils.EMPTY;
    
    /** The projectedunits2. */
    private String projectedunits2 = StringUtils.EMPTY;
    
    /** The group. */
    private String group = StringUtils.EMPTY;
    
    /** The baseline. */
    private String baseline = StringUtils.EMPTY;
    
    /** The methodology. */
    private String methodology = StringUtils.EMPTY;
    
    /** The product growth. */
    private String productGrowth = StringUtils.EMPTY;
    
    /** The account growth. */
    private String accountGrowth = StringUtils.EMPTY;

    
    /** The level one. */
    private String levelOne = StringUtils.EMPTY;
    
    /** The level two. */
    private String levelTwo = StringUtils.EMPTY;
    
    /** The level three. */
    private String levelThree = StringUtils.EMPTY;
    
    /** The level four. */
    private String levelFour = StringUtils.EMPTY;

    /** The level five. */
    private String levelFive = StringUtils.EMPTY;
    
    /** The level six. */
    private String levelSix = StringUtils.EMPTY;
    
    /** The level seven. */
    private String levelSeven = StringUtils.EMPTY;
    
    /** The level eight. */
    private String levelEight = StringUtils.EMPTY;
    
    /** The level nine. */
    private String levelNine = StringUtils.EMPTY;
    
    /** The level ten. */
    private String levelTen = StringUtils.EMPTY;
    
    /** The current level. */
    private String currentLevel = StringUtils.EMPTY;
    
     /** The product. */
     private String product;
    /**
     * The product name.
     */
    private String productName;
    /**
     * The product description.
     */
    private String productDescription;
    
    /** The customer. */
    private String customer;
    
    /** The actuals. */
    private String actuals;
    
    /** The projections. */
    private String projections;

    /**
     * Gets the product.
     *
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the product.
     *
     * @param product the new product
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the product name.
     *
     * @param productName the new product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets the product description.
     *
     * @return the product description
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the product description.
     *
     * @param productDescription the new product description
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * Gets the customer.
     *
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Sets the customer.
     *
     * @param customer the new customer
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * Gets the actuals.
     *
     * @return the actuals
     */
    public String getActuals() {
        return actuals;
    }

    /**
     * Sets the actuals.
     *
     * @param actuals the new actuals
     */
    public void setActuals(String actuals) {
        this.actuals = actuals;
    }

    /**
     * Gets the projections.
     *
     * @return the projections
     */
    public String getProjections() {
        return projections;
    }

    /**
     * Sets the projections.
     *
     * @param projections the new projections
     */
    public void setProjections(String projections) {
        this.projections = projections;
    }

    /**
     * Gets the product growth.
     *
     * @return the product growth
     */
    public String getProductGrowth() {
        return productGrowth;
    }

    /**
     * Sets the product growth.
     *
     * @param productGrowth the product growth
     */
    public void setProductGrowth(final String productGrowth) {
        this.productGrowth = productGrowth;
    }

    /**
     * Gets the account growth.
     *
     * @return the account growth
     */
    public String getAccountGrowth() {
        return accountGrowth;
    }

    /**
     * Sets the account growth.
     *
     * @param accountGrowth the account growth
     */
    public void setAccountGrowth(final String accountGrowth) {
        this.accountGrowth = accountGrowth;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the level.
     *
     * @param level the level
     */
    public void setLevel(final String level) {
        this.level = level;
    }

    /**
     * Gets the group.
     *
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the group.
     *
     * @param group the group
     */
    public void setGroup(final String group) {
        this.group = group;
    }

    /**
     * Gets the baseline.
     *
     * @return the baseline
     */
    public String getBaseline() {
        return baseline;
    }

    /**
     * Sets the baseline.
     *
     * @param baseline the baseline
     */
    public void setBaseline(final String baseline) {
        this.baseline = baseline;
    }

    /**
     * Gets the methodology.
     *
     * @return the methodology
     */
    public String getMethodology() {
        return methodology;
    }

    /**
     * Sets the methodology.
     *
     * @param methodology the methodology
     */
    public void setMethodology(final String methodology) {
        this.methodology = methodology;
    }

    /**
     * Gets the actualsales1.
     *
     * @return the actualsales1
     */
    public String getActualsales1() {
        return actualsales1;
    }

    /**
     * Sets the actualsales1.
     *
     * @param actualsales1 the actualsales1
     */
    public void setActualsales1(final String actualsales1) {
        this.actualsales1 = actualsales1;
    }

    /**
     * Gets the projectedsales1.
     *
     * @return the projectedsales1
     */
    public String getProjectedsales1() {
        return projectedsales1;
    }

    /**
     * Sets the projectedsales1.
     *
     * @param projectedsales1 the projectedsales1
     */
    public void setProjectedsales1(final String projectedsales1) {
        this.projectedsales1 = projectedsales1;
    }

    /**
     * Gets the actualunits1.
     *
     * @return the actualunits1
     */
    public String getActualunits1() {
        return actualunits1;
    }

    /**
     * Sets the actualunits1.
     *
     * @param actualunits1 the actualunits1
     */
    public void setActualunits1(final String actualunits1) {
        this.actualunits1 = actualunits1;
    }

    /**
     * Gets the projectedunits1.
     *
     * @return the projectedunits1
     */
    public String getProjectedunits1() {
        return projectedunits1;
    }

    /**
     * Sets the projectedunits1.
     *
     * @param projectedunits1 the projectedunits1
     */
    public void setProjectedunits1(final String projectedunits1) {
        this.projectedunits1 = projectedunits1;
    }

    /**
     * Gets the actualsales2.
     *
     * @return the actualsales2
     */
    public String getActualsales2() {
        return actualsales2;
    }

    /**
     * Sets the actualsales2.
     *
     * @param actualsales2 the actualsales2
     */
    public void setActualsales2(final String actualsales2) {
        this.actualsales2 = actualsales2;
    }

    /**
     * Gets the projectedsales2.
     *
     * @return the projectedsales2
     */
    public String getProjectedsales2() {
        return projectedsales2;
    }

    /**
     * Sets the projectedsales2.
     *
     * @param projectedsales2 the projectedsales2
     */
    public void setProjectedsales2(final String projectedsales2) {
        this.projectedsales2 = projectedsales2;
    }

    /**
     * Gets the actualunits2.
     *
     * @return the actualunits2
     */
    public String getActualunits2() {
        return actualunits2;
    }

    /**
     * Sets the actualunits2.
     *
     * @param actualunits2 the actualunits2
     */
    public void setActualunits2(final String actualunits2) {
        this.actualunits2 = actualunits2;
    }

    /**
     * Gets the projectedunits2.
     *
     * @return the projectedunits2
     */
    public String getProjectedunits2() {
        return projectedunits2;
    }

    /**
     * Sets the projectedunits2.
     *
     * @param projectedunits2 the projectedunits2
     */
    public void setProjectedunits2(final String projectedunits2) {
        this.projectedunits2 = projectedunits2;
    }

    /**
     * Gets the actualsales.
     *
     * @return the actualsales
     */
    public String getActualsales() {
        return actualsales;
    }

    /**
     * Sets the actualsales.
     *
     * @param actualsales the actualsales
     */
    public void setActualsales(final String actualsales) {
        this.actualsales = actualsales;
    }

    /**
     * Gets the projectedsales.
     *
     * @return the projectedsales
     */
    public Date getProjectedsales() {
        return projectedsales == null ? null : (Date) projectedsales.clone();
    }

    /**
     * Sets the projectedsales.
     *
     * @param projectedsales the projectedsales
     */
    public void setProjectedsales(final Date projectedsales) {
        this.projectedsales = projectedsales == null ? null : (Date) projectedsales.clone();
    }

    /**
     * Gets the actualunits.
     *
     * @return the actualunits
     */
    public Double getActualunits() {
        return actualunits;
    }

    /**
     * Sets the actualunits.
     *
     * @param actualunits the actualunits
     */
    public void setActualunits(final Double actualunits) {
        this.actualunits = actualunits;
    }

    /**
     * Gets the projectedunits.
     *
     * @return the projectedunits
     */
    public String getProjectedunits() {
        return projectedunits;
    }

    /**
     * Sets the projectedunits.
     *
     * @param projectedunits the projectedunits
     */
    public void setProjectedunits(final String projectedunits) {
        this.projectedunits = projectedunits;
    }

    /**
     * Gets the check.
     *
     * @return the check
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * Sets the check.
     *
     * @param check the check
     */
    public void setCheck(final boolean check) {
        this.check = check;
    }

	/**
	 * Gets the level one.
	 *
	 * @return the levelOne
	 */
	public String getLevelOne() {
		return levelOne;
	}

	/**
	 * Sets the level one.
	 *
	 * @param levelOne the levelOne to set
	 */
	public void setLevelOne(final String levelOne) {
		this.levelOne = levelOne;
	}

	/**
	 * Gets the level two.
	 *
	 * @return the levelTwo
	 */
	public String getLevelTwo() {
		return levelTwo;
	}

	/**
	 * Sets the level two.
	 *
	 * @param levelTwo the levelTwo to set
	 */
	public void setLevelTwo(final String levelTwo) {
		this.levelTwo = levelTwo;
	}

	/**
	 * Gets the level three.
	 *
	 * @return the levelThree
	 */
	public String getLevelThree() {
		return levelThree;
	}

	/**
	 * Sets the level three.
	 *
	 * @param levelThree the levelThree to set
	 */
	public void setLevelThree(final String levelThree) {
		this.levelThree = levelThree;
	}

	/**
	 * Gets the level four.
	 *
	 * @return the levelFour
	 */
	public String getLevelFour() {
		return levelFour;
	}

	/**
	 * Sets the level four.
	 *
	 * @param levelFour the levelFour to set
	 */
	public void setLevelFour(final String levelFour) {
		this.levelFour = levelFour;
	}

	/**
	 * Gets the level five.
	 *
	 * @return the levelFive
	 */
	public String getLevelFive() {
		return levelFive;
	}

	/**
	 * Sets the level five.
	 *
	 * @param levelFive the levelFive to set
	 */
	public void setLevelFive(final String levelFive) {
		this.levelFive = levelFive;
	}

	/**
	 * Gets the level six.
	 *
	 * @return the levelSix
	 */
	public String getLevelSix() {
		return levelSix;
	}

	/**
	 * Sets the level six.
	 *
	 * @param levelSix the levelSix to set
	 */
	public void setLevelSix(final String levelSix) {
		this.levelSix = levelSix;
	}

	/**
	 * Gets the level seven.
	 *
	 * @return the levelSeven
	 */
	public String getLevelSeven() {
		return levelSeven;
	}

	/**
	 * Sets the level seven.
	 *
	 * @param levelSeven the levelSeven to set
	 */
	public void setLevelSeven(final String levelSeven) {
		this.levelSeven = levelSeven;
	}

	/**
	 * Gets the level eight.
	 *
	 * @return the levelEight
	 */
	public String getLevelEight() {
		return levelEight;
	}

	/**
	 * Sets the level eight.
	 *
	 * @param levelEight the levelEight to set
	 */
	public void setLevelEight(final String levelEight) {
		this.levelEight = levelEight;
	}

	/**
	 * Gets the level nine.
	 *
	 * @return the levelNine
	 */
	public String getLevelNine() {
		return levelNine;
	}

	/**
	 * Sets the level nine.
	 *
	 * @param levelNine the levelNine to set
	 */
	public void setLevelNine(final String levelNine) {
		this.levelNine = levelNine;
	}

	/**
	 * Gets the level ten.
	 *
	 * @return the levelTen
	 */
	public String getLevelTen() {
		return levelTen;
	}

	/**
	 * Sets the level ten.
	 *
	 * @param levelTen the levelTen to set
	 */
	public void setLevelTen(final String levelTen) {
		this.levelTen = levelTen;
	}

	/**
	 * Gets the current level.
	 *
	 * @return the currentLevel
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * Sets the current level.
	 *
	 * @param currentLevel the currentLevel to set
	 */
	public void setCurrentLevel(final String currentLevel) {
		this.currentLevel = currentLevel;
	}
	 
    
}
