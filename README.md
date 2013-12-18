# Image Editor

## Project description

A Simple image processing tool. This tool was made with educational purposes. It's not fast the code is fairly
easy to study and change. Also, the application allow students to test and combine several different image operations
with ease.

When using this application it's recommended to use -XMX parameter to increase VM's memory limit:
 java -Xmx2048m

Image operations:
- Rotation (90, 180 and 270 degrees)
- Vertical and horizontal flipping
- Subtraction
- Just borders

Filters:
- Noise smoothing: linear and median
- Edge detection: Prewitt, Sobel and Laplace
- Equalization

Morphological operators:
- Erosion
- Dilation
- Opening
- Closing
- Reconstruction
- Granulometry: by erosion and opening and closing (ASF)

## Screenshots

### Color Morphology

![Color morphology](https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-ash2/317062_1964235916858_1651335977_n.jpg)
 1. Center: Original image scaled to 1/2
 2. Top left: Erosion with cross kernel in 7x
 3. Superior right: Dilation with cross kernel in 7x
 4. Bottom left: Opening with cross with 7x
 5. Bottom right: Closing with cross with 7x

### Morphological reconstruction

![Morphological reconstruction](https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-ash2/310260_1964246717128_1334630950_n.jpg)
 1. Top left: Original image
 2. Top right: Image negation
 3. Bottom left: Border pixels extraction
 4. Bottom right: Morphological reconstruction
 5. Center: Negation (2) subtraction from reconstruction (4)

### Borders by erosion

![Borders by Erosion](https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-prn1/301235_1964252037261_1233256122_n.jpg)
 1. Top left: Original image
 2. Too right: Morphological erosion
 3. Bottom left: Subtracted original (1) minus eroded (2)

### Other operations

![Miscelaneous](https://fbcdn-sphotos-f-a.akamaihd.net/hphotos-ak-frc3/287228_1836338199495_8089762_o.jpg)
 1. Top left: Original image
 2. Top right: Edge detection with Sobel operator
 3. Bottom left: Morphological opening with 2 iterations
 4. Bottom right: Morphological closing with 2 iterations
 5. Center: Equalization

## Change History

- 15/dez/2013 - Translated to english and inserted in GitHub