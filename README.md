# Horserace-Simulation
## Program Description:
The idea is to create a graphical program using JavaFX that will race horses across a window in Java. The program will be an application that will instantiate multiple instances of Horse threads. The application will run in a window that will contain three buttons:
1. `[run]`
2. `[reset]`
3. `[quit]`

A total of 6 horses will run the race.

When the application starts, the horses will be lined up at the starting line. When the user clicks the `[run]` button, the horses run. The program will animate their running across the window, ultimately declaring a winner.

When a horse crosses the finish line, the program will declare a winner via a dialog box. The dialog box will also report the winning time. The timer returns milliseconds which the program will convert into seconds and report in the dialog box.

The user will click the dialog to close it. If the user then presses the [reset] button the horses should be redrawn back at the starting line. If the user clicks the [quit] button, the program should terminate. All the buttons should work properly. Some interface design in alerting the user how to use the interface will be needed. Be sure the program does not generate runtime errors if the buttons are clicked in an incorrect pattern.
