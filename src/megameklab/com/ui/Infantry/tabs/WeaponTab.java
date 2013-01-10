/*
 * MegaMekLab - Copyright (C) 2008 
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Infantry.tabs;

import javax.swing.SpringLayout;

import megamek.common.Infantry;
import megameklab.com.ui.Infantry.views.WeaponView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;

public class WeaponTab extends ITab {

    /**
     * 
     */
    private static final long serialVersionUID = 3978675469713289404L;
    private WeaponView weapon;
    private RefreshListener refresh;

    public WeaponTab(Infantry unit) {
        this.unit = unit;
        weapon = new WeaponView(unit);
        setLayout(new SpringLayout());
        this.add(weapon);
        SpringLayoutHelper.setupSpringGrid(this, 1);

        refresh();
    }

    public void refresh() {
        weapon.updateUnit(unit);
        weapon.refresh();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        weapon.addRefreshedListener(refresh);
    }

    public WeaponView getView() {
        return weapon;
    }

}