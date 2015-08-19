#ARBaseLib
A modified version of the ARBaseLib of the ARToolKit Android SDK for use in marker tracking and surface rendering.

##Synopsis
This project extends the capabilities of the ARBaseLib by including new packages for `readers` and `rendering`.

##Motivation
The goal of this project is to develop a head mounted display (HMD) for surgeons to use as a navigational aid during a procedure.
The Wearable Intelligent Navigation System for Surgery (WINSS) Augmented Reality (AR) system allows for image guided surgery without the need to occupy a surgeon’s hands or eyes. 
Rather than providing high-resolution preoperative images, the HMD will show derived graphics and models, 
such as tumor outlines and target points in a “picture in picture” manner, to assist in navigation. 
By using the AR device in conjunction with other sensors for optical tracking and inertial sensing, 
the system can operate even when markers on the patient frame are occluded from the view of a tracker. 

##Installation and Testing
This project uses the [modified version of the ARBaseLib package](https://github.com/pranavl/ARwinss) of ARToolKit.
After compiling the ARBaseLib package, this project can be compiled as an Android Application Project.

The Android version of the ARToolKit SDK is implemented using a Java wrapper around native C++ code, compiled using the Java Native Interface (JNI).

The application was built and tested on the [Epson Moverio BT-200 smart glasses](http://www.epson.com/cgi-bin/Store/jsp/Landing/moverio-bt-200-smart-glasses.do?ref=van_moverio_2014).

##Code Example
###`org.artoolkit.ar.base.rendering`
This package contains the general representation of a surface that can be rendered along with some sample shapes.
The `Shape` abstract class defines the elements needed to render a surface, namely a collection of points defined by coordinates, 
the indices of each point that defines a face of the surface, and an array of colors corresponding to each point.
In addition, every shape can be transformed by rotations, translation, and scaling using methods defined in the 'Shape' class.

###`org.artoolkit.ar.base.readers`

##API Reference
The original library can be found at http://artoolkit.org/ with the [original documentation](http://artoolkit.org/documentation/).

##Contributors
This project was made possible with support from Dr. Peter Kazanzides and Dr. Sungmin Kim
of the Laboratory of Computational Sensing and Robotics at Johns Hopkins University.
