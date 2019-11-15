package org.javabip.spec.clientserver;

import java.util.ArrayList;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.api.PortType;
import org.javabip.api.DataOut.AccessType;

@Ports({ @Port(name = "sync", type = PortType.enforceable),
	@Port(name = "get", type = PortType.enforceable),
	@Port(name = "free", type = PortType.enforceable)})
@ComponentType(initial = "req", name = "org.bip.spec.clientserver.Resource")
public class Server {

	private ArrayList<Resource> listResource;
	private int serverId;
	
	public Server(int _id) {
		// TODO Auto-generated constructor stub
		serverId = _id;
		listResource = new ArrayList<Resource>();
	}
	
	public Server(int _id, Resource... args) {
		serverId = _id;
		listResource = new ArrayList<Resource>();
		
		for (Resource res : args) {
			if (!isExistedInResourceList(res)) {
				listResource.add(res);
			}
		}
	}
	
//	@Guard(name = "resourceIsExist")
//	public boolean resIsExist(@Data(name = "resourceId") int resourceId) {
//		for (Resource res : listResource) {
//			if (resourceId == res.getId()) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public Resource getResource(@Data(name = "resourceId") int resourceId) {
//		return listResource.get(resourceId);
//	}
	
	@Data(name = "serverId", accessTypePort = AccessType.any)
	public int getServerId() {
		return serverId;
	}
	
	@Data(name = "listResource", accessTypePort = AccessType.any)
	public ArrayList<Resource> getListResource(){
		return listResource;
	}
	
	@Guard(name = "canMove")
	public boolean canMove(int resourceId) {
		Resource needToMove = getResourceWithId(resourceId);
		if (needToMove != null) {
			return needToMove.resourceAvailable();
		}
		return false;
	}
	
	public boolean isExistedInResourceList(Resource res) {
		for (Resource cur : listResource) {
			if (cur.getId() == res.getId())
				return true;
		}
		return false;
	}
	
	public Resource getResourceWithId(int id) {
		for (Resource cur : listResource) {
			if (cur.getId() == id) {
				return cur;
			}
		}
		return null;
	}
}
