package org.javabip.spec.petrinet;

import java.util.ArrayList;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;

@Ports({ @Port(name = "sync", type = PortType.enforceable)})
@ComponentType(initial = "zero", name = "org.bip.spec.petrinet.SynchronizerResource")
public class SynchronizerResource {
	
	private ArrayList<AnnoPetri> listAtom;
	
	public SynchronizerResource(ArrayList<AnnoPetri> _listAtom) {
		// TODO Auto-generated constructor stub
		listAtom = _listAtom;
		//resourceAvailable = -1;
	}

	@Transition(name = "sync", source = "zero", target = "zero", guard = "available")
	public void synchronizing() {
		String rs = "";
		for (AnnoPetri a :  listAtom) {
			rs += a.atomId() + " ";
		}
		System.out.println("\t  " + rs + ": synchronize");
		
	}
	
	@Guard(name = "available")
	public boolean canInteract(@Data(name = "resourceAvailable") int resourceAvail) {
		if (resourceAvail == 0) {
			System.out.println("\t --- Resource can be freed.");
			return true;
		}
		
		return false;
	}

}
