public class NBody {
    public static double readRadius(String f) {
        /* start reading in given file. */
        In in = new In(f);
        double R;
        int N;
        /* The first value is an integer N representing the number of planets.
         * The second value is a real number R representing the radius of the universe.
         */
        N = in.readInt();
        R = in.readDouble();
        return R;
    }

    public static Planet[] readPlanets(String f) {
        In in = new In(f);
        int N = in.readInt();
        double R = in.readDouble();
        Planet[] NPlanets = new Planet[N];
        /* Then there are N rows, each row contains 6 values.
         * The first two values are the x- and y-coordinates of the initial position;
         * the next pair of values are the x- and y-components of the initial velocity;
         * the fifth value is the mass;
         * the last value is a String that is the name of an image file used to display the planets.
         */
        for (int i = 0; i < N; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            NPlanets[i] = new Planet(xp, yp, xv, yv, m, img);
        }
        return NPlanets;
    }

    /* main method */
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        /* Read in the planets and the universe radius from the file. */
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        /* start playing music */
        StdAudio.play("audio/2001.mid");
        /* Sets up the universe so it goes from -R, -R up to R, R */
        StdDraw.setScale(-radius, radius);
        /* Clears the drawing window. */
        StdDraw.clear();
        /* Draw the background image. */
        StdDraw.picture(0, 0, "images/starfield.jpg");
        /* Draw all of the planets. */
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.enableDoubleBuffering();
        /* a loop to loop until time variable t is T */
        for (double t = 0; t < T; t += dt) {
            /* Calculate the net x and y forces for each planet,
             * storing these in the xForces and yForces arrays respectively.
             */
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            /* Call update on each of the planets.
             * This will update each planet’s position, velocity, and acceleration.
             */
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            /* Draw the background image. */
            StdDraw.picture(0, 0, "images/starfield.jpg");
            /* Shows the drawing to the screen. */
            StdDraw.show();
            /* Draw all of the planets. */
            for (Planet p : planets) {
                p.draw();
            }
            /* Shows the drawing to the screen. */
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
