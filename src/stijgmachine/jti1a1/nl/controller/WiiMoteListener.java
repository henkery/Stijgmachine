package stijgmachine.jti1a1.nl.controller;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;


public interface WiiMoteListener extends java.util.EventListener
{
	void onButtonsEvent(WiimoteButtonsEvent event);
	
	void onIrEvent(IREvent event);
	
	void onMotionSensingEvent(MotionSensingEvent event);
	
	void onExpansionEvent(ExpansionEvent event);
	
	void onStatusEvent(StatusEvent event);
	
    void onDisconnectionEvent(DisconnectionEvent event);

    void onNunchukInsertedEvent(NunchukInsertedEvent event);

    void onNunchukRemovedEvent(NunchukRemovedEvent event);
    
    void onSwipeEvent(SwipeEvent event);
    
}
