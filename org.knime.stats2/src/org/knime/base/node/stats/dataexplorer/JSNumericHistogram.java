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
 *   Oct 19, 2017 (annaz): created
 */
package org.knime.base.node.stats.dataexplorer;

import org.knime.base.data.statistics.HistogramColumn;
import org.knime.base.data.statistics.HistogramModel;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.property.hilite.HiLiteHandler;

/**
 *
 * @author Anastasia Zhukova, KNIME GmbH, Konstanz, Germany
 */
public class JSNumericHistogram extends JSHistogram {

    HistogramModel<?> m_histogram;

    /**
     * @param colName
     * @param colIndex
     */
    JSNumericHistogram(final String colName, final int colIndex, final BufferedDataTable table, final double min,
        final double max, final double mean) {
        super(colName, colIndex);
        HistogramColumn hCol = HistogramColumn.getDefaultInstance();
        this.m_histogram = hCol.histograms(table, new HiLiteHandler(), new double[]{min}, new double[]{max}, new double[]{mean}, colName).get(0);
        this.m_bins = m_histogram.getBins();
        this.m_maxCount = m_histogram.getMaxCount();
    }

}
