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
package megameklab.ui.fighterAero;

import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestAero;
import megameklab.ui.EntitySource;
import megameklab.ui.util.IView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Vector;

public class ASSummaryView extends IView{
    private JTextField txtStructTon = new JTextField("?");
    private JTextField txtEngineTon = new JTextField("?");   
    private JTextField txtFuelTon = new JTextField("?");
    private JTextField txtCockpitTon = new JTextField("?");
    private JTextField txtHeatTon = new JTextField("?");
    private JTextField txtArmorTon = new JTextField("?");   
    private JTextField txtEnhancementsTon = new JTextField("?");
    private JTextField txtWeapTon = new JTextField("?");
    private JTextField txtOtherTon = new JTextField("?");

    private EntityVerifier entityVerifier = EntityVerifier.getInstance(
            new File("data/mechfiles/UnitVerifierOptions.xml"));

    public ASSummaryView(EntitySource eSource) {
        super(eSource);

        Vector<JTextField> valueFields = new Vector<>();

        valueFields.add(txtStructTon);
        valueFields.add(txtEngineTon);
        valueFields.add(txtFuelTon);        
        valueFields.add(txtCockpitTon);
        valueFields.add(txtHeatTon);
        valueFields.add(txtArmorTon);
        valueFields.add(txtEnhancementsTon);
        valueFields.add(txtWeapTon);
        valueFields.add(txtOtherTon);

        Dimension size = new Dimension(45, 25);
        for (JTextField field : valueFields) {
            field.setEditable(false);
            field.setSize(size);
            field.setPreferredSize(size);
            field.setMinimumSize(size);
            field.setMaximumSize(size);
            field.setHorizontalAlignment(SwingConstants.RIGHT);
        }

        valueFields.removeAllElements();

        size = new Dimension(80, 25);
        for (JTextField field : valueFields) {
            field.setEditable(false);
            field.setSize(size);
            field.setPreferredSize(size);
            field.setMinimumSize(size);
            field.setMaximumSize(size);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        size = new Dimension(120, 25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        this.add(createLabel("Category", size, SwingConstants.CENTER), gbc);
        gbc.gridy = 1;
        this.add(createLabel("Internal Structure:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 2;
        this.add(createLabel("Engine:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 3;
        this.add(createLabel("Fuel:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 4;
        this.add(createLabel("Cockpit:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 5;
        this.add(createLabel("Heat Sinks:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 6;
        this.add(createLabel("Armor:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 7;
        this.add(createLabel("Enhancements:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 9;
        this.add(createLabel("Equipment", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 10;
        this.add(createLabel("Weapons:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 11;
        this.add(createLabel("Other:", size, SwingConstants.RIGHT), gbc);

        size = new Dimension(45, 25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(createLabel("Ton", size, SwingConstants.CENTER), gbc);
        gbc.gridy = 1;
        this.add(txtStructTon, gbc);
        gbc.gridy = 2;
        this.add(txtEngineTon, gbc);
        gbc.gridy = 3;
        this.add(txtFuelTon, gbc);
        gbc.gridy = 4;
        this.add(txtCockpitTon, gbc);
        gbc.gridy = 5;
        this.add(txtHeatTon, gbc);
        gbc.gridy = 6;
        this.add(txtArmorTon, gbc);
        gbc.gridy = 7;
        this.add(txtEnhancementsTon, gbc);
        gbc.gridy = 10;
        this.add(txtWeapTon, gbc);
        gbc.gridy = 11;
        this.add(txtOtherTon, gbc);

        setBorder(BorderFactory.createTitledBorder("Summary"));
    }

    private JLabel createLabel(String text, Dimension size, int align) {
        JLabel label = new JLabel(text, SwingConstants.TRAILING);
        setFieldSize(label, size);
        label.setHorizontalAlignment(align);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void refresh() {
        TestAero testAero = new TestAero(getAero(), entityVerifier.aeroOption, null);
       
        txtStructTon.setText(Double.toString(testAero.getWeightStructure()));
        txtEngineTon.setText(Double.toString(testAero.getWeightEngine()));
        txtFuelTon.setText(Double.toString(testAero.getWeightFuel()));
        txtCockpitTon.setText(Double.toString(testAero.getWeightControls()));
        txtHeatTon.setText(Double.toString(testAero.getWeightHeatSinks()));        
        txtArmorTon.setText(Double.toString(testAero.getWeightArmor()));
        txtEnhancementsTon.setText(Double.toString(testAero.getWeightMisc()));
        txtWeapTon.setText(Double.toString(testAero.getWeightWeapon()));
        txtOtherTon.setText(Double.toString(testAero.getWeightPowerAmp() + 
                testAero.getWeightCarryingSpace() + testAero.getWeightMisc()));
    }
}