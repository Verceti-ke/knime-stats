/*
 * ------------------------------------------------------------------------
 *
 *  Copyright by KNIME GmbH, Konstanz, Germany
 *  Website: http://www.knime.com; Email: contact@knime.com
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
 *   Jul 19, 2016 (winter): created
 */
package org.knime.base.node.stats.testing.wilcoxonsignedrank2;

import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataTableSpecCreator;
import org.knime.core.data.MissingCell;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;

/**
 * @author Patrick Winter, University of Konstanz
 */
public class RankStatistics {

    private int m_pN = 0;

    private int m_nN = 0;

    private int m_ties = 0;

    private double m_pSum = 0;

    private double m_nSum = 0;

    /**
     * Adds the given rank to the statistics. This method detects automatically if the rank is negative or positive.
     *
     * @param rank The rank to add to the staticstics.
     */
    public void addRank(final double rank) {
        if (rank < 0) {
            m_nN++;
            m_nSum -= rank;
        } else {
            m_pN++;
            m_pSum += rank;
        }
    }

    /**
     * Adds one to the count of ties.
     */
    public void addTie() {
        m_ties++;
    }

    /**
     * Creates the specs of the rank statistics table.
     *
     * @return The table specs.
     */
    public static DataTableSpec createSpec() {
        DataTableSpecCreator tableSpecCreator = new DataTableSpecCreator();
        tableSpecCreator.addColumns(new DataColumnSpecCreator("Test", IntCell.TYPE).createSpec());
        tableSpecCreator.addColumns(new DataColumnSpecCreator("Type", StringCell.TYPE).createSpec());
        tableSpecCreator.addColumns(new DataColumnSpecCreator("N", IntCell.TYPE).createSpec());
        tableSpecCreator.addColumns(new DataColumnSpecCreator("Mean Rank", DoubleCell.TYPE).createSpec());
        tableSpecCreator.addColumns(new DataColumnSpecCreator("Sum of Ranks", DoubleCell.TYPE).createSpec());
        return tableSpecCreator.createSpec();
    }

    /**
     * Adds these rank statistics to the given table.
     *
     * @param testNr Number of the test that these statistics belong to. Will be added as the first column.
     * @param container The table to add to.
     */
    public void addRowsToTable(final int testNr, final BufferedDataContainer container) {
        container.addRowToTable(
            new DefaultRow("Row" + container.size(), new IntCell(testNr), new StringCell("Negative Ranks"),
                new IntCell(m_nN), new DoubleCell(m_nSum / m_nN), new DoubleCell(m_nSum)));
        container.addRowToTable(
            new DefaultRow("Row" + container.size(), new IntCell(testNr), new StringCell("Positive Ranks"),
                new IntCell(m_pN), new DoubleCell(m_pSum / m_pN), new DoubleCell(m_pSum)));
        container.addRowToTable(new DefaultRow("Row" + container.size(), new IntCell(testNr), new StringCell("Ties"),
            new IntCell(m_ties), new MissingCell(""), new MissingCell("")));
        container.addRowToTable(new DefaultRow("Row" + container.size(), new IntCell(testNr), new StringCell("Total"),
            new IntCell(m_nN + m_pN + m_ties), new MissingCell(""), new MissingCell("")));
    }

}
