package com.juphoon.data;

import com.juphoon.JCEngine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class USEventHandler {

    private Set<SimpleEventHandler> mEventHandlers;
    public USEventHandler() {
        mEventHandlers = new HashSet<>();
    }

    public void addEventHandler(SimpleEventHandler eventHandler) {
        if (mEventHandlers == null) return;
        mEventHandlers.add(eventHandler);
    }

    public void removeEventHandler(SimpleEventHandler eventHandler) {
        if (mEventHandlers == null) return;
        mEventHandlers.remove(eventHandler);
    }

    public JCEngine.JCEngineEventHandler eventHandler = new JCEngine.JCEngineEventHandler() {

        @Override
        public void onReconnecting() {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onReconnecting();
            }
        }

        @Override
        public void onConnected(String userId) {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onConnected(userId);
            }
        }

        @Override
        public void onDisconnected(@JCEngine.DisconnectReason int reason) {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onDisconnected(reason);
            }
        }

        @Override
        public void onJoinRoomSuccess() {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onJoinRoomSuccess();
            }
        }

        @Override
        public void onRoomTitleUpdated() {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onRoomTitleUpdated();
            }
        }

        @Override
        public void onRoomPropertyUpdated() {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onRoomPropertyUpdated();
            }
        }

        @Override
        public void onLeftRoom(@JCEngine.LeaveReason int reason) {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onLeftRoom(reason);
            }
        }

        @Override
        public void onRoomClear() {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onRoomClear();
            }
        }

        @Override
        public void onParticipantJoin(String userId) {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onParticipantJoin(userId);
            }
        }

        @Override
        public void onParticipantLeft(String userId, @JCEngine.LeaveReason int reason) {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onParticipantLeft(userId, reason);
            }
        }

        @Override
        public void onParticipantUpdated(String userId) {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onParticipantUpdated(userId);
            }
        }

        @Override
        public void onScreenShareStateChanged(boolean active) {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onScreenShareStateChanged(active);
            }
        }

        @Override
        public void onDataReceived(String key, String content, String fromUserId) {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onDataReceived(key, content, fromUserId);
            }
        }

        @Override
        public void onWarning(int warningCode) {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onWarning(warningCode);
            }
        }

        @Override
        public void onError(@JCEngine.ErrorCode int errorCode) {
            Iterator<SimpleEventHandler> iterator = mEventHandlers.iterator();
            while (true) {
                if (!(iterator.hasNext())) break;
                SimpleEventHandler eventHandler = iterator.next();
                eventHandler.onError(errorCode);
            }
        }
    };

    public static class SimpleEventHandler implements JCEngine.JCEngineEventHandler {

        @Override
        public void onReconnecting() {

        }

        @Override
        public void onConnected(String userId) {

        }

        @Override
        public void onDisconnected(@JCEngine.DisconnectReason int reason) {

        }

        @Override
        public void onJoinRoomSuccess() {

        }

        @Override
        public void onRoomTitleUpdated() {

        }

        @Override
        public void onRoomPropertyUpdated() {

        }

        @Override
        public void onLeftRoom(@JCEngine.LeaveReason int reason) {

        }

        @Override
        public void onRoomClear() {

        }

        @Override
        public void onParticipantJoin(String userId) {

        }

        @Override
        public void onParticipantLeft(String userId, @JCEngine.LeaveReason int reason) {

        }

        @Override
        public void onParticipantUpdated(String userId) {

        }

        @Override
        public void onScreenShareStateChanged(boolean active) {

        }

        @Override
        public void onDataReceived(String key, String content, String fromUserId) {

        }

        @Override
        public void onWarning(int warningCode) {

        }

        @Override
        public void onError(@JCEngine.ErrorCode int errorCode) {

        }
    }
}
