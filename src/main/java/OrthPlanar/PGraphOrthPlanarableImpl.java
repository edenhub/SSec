package OrthPlanar;

import DBReverse.DBReverser;
import DBReverse.TableStc;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.intermediate.ExternalFaceProcessor;
import de.cau.cs.kieler.klay.planar.intermediate.GridDrawingProcessor;
import de.cau.cs.kieler.klay.planar.intermediate.GridRepresentation;
import de.cau.cs.kieler.klay.planar.intermediate.RectShapeProcessor;
import de.cau.cs.kieler.klay.planar.p1planar.BoyerMyrvoldPlanarSubgraphBuilder;
import de.cau.cs.kieler.klay.planar.p1planar.EdgeInsertionPlanarization;
import de.cau.cs.kieler.klay.planar.p2ortho.TamassiaOrthogonalizer;
import de.cau.cs.kieler.klay.planar.p3compact.TidyRectangleCompactor;
import de.cau.cs.kieler.klay.planar.properties.Properties;
import de.cau.cs.kieler.klay.planar.util.PUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lab on 2015/3/9.
 */
public class PGraphOrthPlanarableImpl implements PGraphOrthPlanarable {

    private PGraph graph;
    private OrthPlanarRepren orthPlanarRepren;
    private DBReverser dbReverser;

    @Override
    public void transform() {
        List<TableStc> tableStcs = dbReverser.getTableStcs();
        Map<String,Integer> graphMapping = dbReverser.getGraphMapping();

        graph = new PGraph();
        List<PNode> nodes = new ArrayList<PNode>(tableStcs.size());
        for (TableStc tableStc : tableStcs){
            PNode pNode = graph.addNode(PNode.NodeType.NORMAL);
            nodes.add(pNode);
        }

        for (Map.Entry<String,Integer> entry : graphMapping.entrySet()){
            int currId = entry.getValue();
            TableStc currTable = tableStcs.get(currId);

            Set<String> foreginTables = currTable.getForeignTables();
            for (String tableName : foreginTables){
                int foreginId = graphMapping.get(tableName);
                graph.addEdge(nodes.get(currId),nodes.get(foreginId));
            }
        }
    }

    public void orthPlanar(DBReverser dbReverser){
        transform();//将图信息转化为pgraph的形式

        BoyerMyrvoldPlanarSubgraphBuilder subgraphBuilder = new BoyerMyrvoldPlanarSubgraphBuilder();
        subgraphBuilder.process(graph);

        EdgeInsertionPlanarization insertionPlanarization = new EdgeInsertionPlanarization();
        insertionPlanarization.process(graph);

        ExternalFaceProcessor externalFaceProcessor = new ExternalFaceProcessor();
        externalFaceProcessor.process(graph);

        TamassiaOrthogonalizer tamassiaOrthogonalizer = new TamassiaOrthogonalizer();
        tamassiaOrthogonalizer.process(graph);

        RectShapeProcessor rectShapeProcessor = new RectShapeProcessor();
        rectShapeProcessor.process(graph);

        PUtil.defineFaceSideEdges(graph);

        TidyRectangleCompactor rectangleCompactor = new TidyRectangleCompactor();
        rectangleCompactor.process(graph);

        GridDrawingProcessor gridDrawingProcessor = new GridDrawingProcessor();
        gridDrawingProcessor.process(graph);

        GridRepresentation gridRepresentation = graph.getProperty(Properties.GRID_REPRESENTATION);

//        生成OrthPlanarRepre实例
    }

    @Override
    public void orthPlanar() {
        orthPlanar(dbReverser);
    }


    public OrthPlanarRepren getOrthPlanarRepren() {
        return orthPlanarRepren;
    }

    public void setOrthPlanarRepren(OrthPlanarRepren orthPlanarRepren) {
        this.orthPlanarRepren = orthPlanarRepren;
    }

    public void setDbReverser(DBReverser dbReverser) {
        this.dbReverser = dbReverser;
    }
}
