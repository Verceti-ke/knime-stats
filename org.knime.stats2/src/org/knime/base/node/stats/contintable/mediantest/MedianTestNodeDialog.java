/*
 * ------------------------------------------------------------------------
 *
 *  Copyright by KNIME GmbH, Konstanz, Germany
 *  Website: http://www.knime.org; Email: contact@knime.org
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License, Version 3, as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 *  Additional permission under GNU GPL version 3 section 7:
 *
 *  KNIME interoperates with ECLIPSE solely via ECLIPSE's plug-in APIs.
 *  Hence, KNIME and ECLIPSE are both independent programs and are not
 *  derived from each other. Should, however, the interpretation of the
 *  GNU GPL Version 3 ("License") under any applicable laws result in
 *  KNIME and ECLIPSE being a combined program, KNIME GMBH herewith grants
 *  you the additional permission to use and propagate KNIME together with
 *  ECLIPSE with only the license terms in place for ECLIPSE applying to
 *  ECLIPSE and the GNU GPL Version 3 applying for KNIME, provided the
 *  license terms of ECLIPSE themselves allow for the respective use and
 *  propagation of ECLIPSE together with KNIME.
 *
 *  Additional permission relating to nodes for KNIME that extend the Node
 *  Extension (and in particular that are based on subclasses of NodeModel,
 *  NodeDialog, and NodeView) and that only interoperate with KNIME through
 *  standard APIs ("Nodes"):
 *  Nodes are deemed to be separate and independent programs and to not be
 *  covered works.  Notwithstanding anything to the contrary in the
 *  License, the License does not apply to Nodes, you are not required to
 *  license Nodes under the License, and you are granted a license to
 *  prepare and propagate Nodes, in each case even if such Nodes are
 *  propagated with or for interoperation with KNIME.  The owner of a Node
 *  may freely choose the license terms applicable to such Node, including
 *  when such Node is propagated with or for interoperation with KNIME.
 * ---------------------------------------------------------------------
 *
 * History
 *   June 14, 2016 (sampson): created
 */

package org.knime.base.node.stats.contintable.mediantest;

import org.knime.core.data.DoubleValue;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;

/**
 * <code>NodeDialog</code> for the "MedianTest" Node.
 *
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows creation of a simple dialog with standard
 * components. If you need a more complex dialog please derive directly from {@link org.knime.core.node.NodeDialogPane}.
 *
 * @author Oliver Sampson, University of Konstanz
 */
public class MedianTestNodeDialog extends DefaultNodeSettingsPane {

    private static final int INPUT_WIDTH = 5;


    /**
     * New pane for configuring MedianTest node dialog. This is just a suggestion to demonstrate possible default dialog
     * components.
     */
    @SuppressWarnings("unchecked")
    protected MedianTestNodeDialog() {
        super();

        addDialogComponent(new DialogComponentColumnNameSelection(MedianTestNodeModel.createSettingsModelCol1(),
            "First Column:", MedianTestNodeModel.PORT_IN_DATA, true, DoubleValue.class));

        addDialogComponent(new DialogComponentColumnNameSelection(MedianTestNodeModel.createSettingsModelCol2(),
            "Second Column:", MedianTestNodeModel.PORT_IN_DATA, true, DoubleValue.class));

        addDialogComponent(new DialogComponentNumber(MedianTestNodeModel.createSettingsModelConfidenceLevel(),
            "Confidence Level", MedianTestNodeModel.CONFIDENCE_LEVEL_DEFAULT, INPUT_WIDTH));

        addDialogComponent(new DialogComponentNumber(MedianTestNodeModel.createSettingsModelLaplaceCorrection(),
            "Laplace Correction", MedianTestNodeModel.LAPLACE_CORRECTION_DEFAULT, INPUT_WIDTH));

        addDialogComponent(new DialogComponentBoolean(MedianTestNodeModel.createSettingsModelIgnoreMissingValues(), "Ignore Missing Values"));


    }
}
