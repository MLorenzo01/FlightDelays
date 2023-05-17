package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	private Graph<Airport, DefaultWeightedEdge> graph;
	private ExtFlightDelaysDAO dao;
	private Map<Integer, Airport> idMap;
	
	public Model() {
		this.graph = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.dao = new ExtFlightDelaysDAO();
		this.idMap = new HashMap<>();
		this.dao.loadAllAirports(idMap);
	}
	public void creaGrafo(int nAirlines) {
		this.graph = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(graph, this.dao.getVertici(nAirlines, idMap));
		List<Rotta> edges = this.dao.getRotte(idMap);
		for (Rotta r: edges) {
			Airport origin = r.getOrigin();
			Airport destination = r.getDestination();
			int N = r.getN();
			if(graph.vertexSet().contains(origin) && graph.vertexSet().contains(destination)) {
				DefaultWeightedEdge edge =this.graph.getEdge(origin, destination);
				if(edge != null) {
					double weight = this.graph.getEdgeWeight(edge);
					weight += N;
					this.graph.setEdgeWeight(origin, destination, weight);
				}else {
					this.graph.addEdge(origin, destination);
					this.graph.setEdgeWeight(origin, destination, N);
				}
			}
		}
		System.out.println("Grafo creato /nCi sono " + this.graph.vertexSet().size() + " vertici");
		System.out.println("Ci sono " + this.graph.edgeSet().size() + " edges");

	}
}



















