# COM561-Assignment-01

The following is concurrent application that uses semaphores to handle a simulated train track.

The train track is a figure of 8 that is broken up into 18 segments.
Trains can enter at the top and bottom of the track (Positions 5 and 8) and must cross the junction in the middle of the track (Position 0)
before exiting at the top or bottom of the track where they started

The system can handle multiple trains coming onto and off the track and crossing a junction in the middle of the track.
The system can handle 8 trains concurrently on the track (this is a mathematical limitation as any more than 8 will eventually create a deadlock)

The application was written in Java using IntelliJ Idea as the IDE.
