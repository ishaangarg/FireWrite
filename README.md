# FireWrite

To replicate crash: 
1. Install app in 2 devices
2. keep app in background on device#2
3. send msg from device#1
4. Observe in device#2 logs, that data notif is recvd with log tag "FcmService"
5. open app in device#2
6. crash occurs as sooon as app is foregrounded in device#2
