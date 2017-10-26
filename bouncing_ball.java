//  import libraries
import javax.swing.*;
import java.awt.*;

public class bouncing_ball {

        //  global variables
        private JFrame frame = new JFrame ();
        //  object variables
        public static void main (String [] args) {
                bouncing_ball start = new bouncing_ball ();
                start.ball_movement ();
        }

        void ball_movement () {
                EventQueue.invokeLater (new Runnable () {
                        @Override
                        public void run () {
                                frame.setVisible (true);
                                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                                frame.setSize (500, 500);
                                frame.getContentPane (). add (new ball ());
                        }
                });
        }

}

class ball extends JPanel {

        //  global variables
        bouncing_ball one = new bouncing_ball ();
        //  instance variables
        private int ball_x = 0;
        private int ball_y = 0;
        private int radius = 20;
        private int ball_x_speed = 0;
        private int ball_y_speed = 0;
        private int ball_speed;
        private int ball_delay = 50;

        //  encapsulation
        public void set_ball_x (int x) {
                if (x >= -4 && x <= 4) {
                        ball_x += x;
                }
        }
        public int get_ball_x () {
                return ball_x;
        }

        public void set_ball_y (int y) {
                if (y >= -4 && y <= 4) {
                        ball_y += y;
                }
        }
        public int get_ball_y () {
                return ball_y;
        }

        public void set_ball_x_speed (int x) {
                if (x >= -4 && x <= 4) {
                        ball_x_speed = x;
                }
        }
        public int get_ball_x_speed () {
                return ball_x_speed;
        }

        public void set_ball_y_speed (int y) {
                if (y >= -4 && y <= 4) {
                        ball_y_speed = y;
                }
        }
        public int get_ball_y_speed () {
                return ball_y_speed;
        }

        public void collisions (int z) {
                if (z == 0) {
                        set_ball_x_speed (ball_speed ());
                        set_ball_y_speed (ball_speed ());
                } else if (z == 1) {
                        set_ball_x_speed (ball_speed ());
                        set_ball_y_speed (-(ball_speed ()));
                } else if (z == 2) {
                        set_ball_x_speed (-(ball_speed ()));
                        set_ball_y_speed (ball_speed ());
                } else if (z == 3) {
                        set_ball_x_speed (-(ball_speed ()));
                        set_ball_y_speed (-(ball_speed ()));
                }
        }

        public ball () {
                Thread t = new Thread (new Runnable () {
                        @Override
                        public void run () {
                                while (true) {
                                        //  moving ball
                                        //  top wall
                                        if (get_ball_x () <= 0) {
                                                //  starting ball movement
                                                if (get_ball_x () == 0 && get_ball_y () == 0) {
                                                        collisions (0);
                                                }
                                                //  approaching with a positive y
                                                if (get_ball_x_speed () < 0 &&  get_ball_y_speed () > 0){
                                                        collisions (0);
                                                        //  approaching with a negative y
                                                } else if (get_ball_x_speed () < 0 && get_ball_y_speed () < 0) {
                                                        collisions (1);
                                                }

                                                //  left wall
                                        } else if (get_ball_y () <= 0) {
                                                //  approaching with a positive x
                                                if (get_ball_x_speed () > 0 &&  get_ball_y_speed () < 0){
                                                        collisions (0);
                                                        //  approaching with a negative x
                                                } else if (get_ball_x_speed () < 0 && get_ball_y_speed () < 0) {
                                                        collisions (2);
                                                }
                                                //  right wall
                                        } else if (get_ball_x () >= 480) {
                                                //  approaching with a positive y
                                                if (get_ball_x_speed () > 0 &&  get_ball_y_speed () > 0){
                                                        collisions (2);
                                                        //  approaching with a negative y
                                                } else if (get_ball_x_speed () > 0 && get_ball_y_speed () < 0) {
                                                        collisions (3);
                                                }
                                                //  bottom wall
                                        } else if (get_ball_y () >= 480) {
                                                //  approaching with a positive x
                                                if (get_ball_x_speed () > 0 &&  get_ball_y_speed () > 0){
                                                        collisions (1);
                                                        //  approaching with a negative x
                                                } else if (get_ball_x_speed () < 0 && get_ball_y_speed () > 0) {
                                                        collisions (3);
                                                }

                                        }
                                        set_ball_x (ball_x_speed);
                                        set_ball_y (ball_y_speed);
                                        //  tc
                                        System.out.println (get_ball_x ());
                                        System.out.println (get_ball_y ());

                                        //  timer
                                        try {
                                                Thread.sleep (ball_delay);
                                        } catch (InterruptedException e) {}

                                        repaint ();
                                }
                        }
                });
                t.start ();

        }

        public int ball_speed () {
                ball_speed = (int) (Math.random () * 4) + 1;
                return ball_speed;
        }

        @Override
        public void paint (Graphics g) {
                super.paintComponent(g);
                g.fillOval (get_ball_x (), get_ball_y (), radius, radius);
        }

}
