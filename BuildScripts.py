import subprocess
import shutil
import sys
import os

def main(cliArgumnets):
    rootPath = os.path.dirname(os.path.realpath(__file__))
    action = cliArgumnets[1]
    
    match action:
        case "cpp":
            cppBuild(rootPath)
        case "clean-cpp":
            cleanCpp(rootPath)
            
def cppBuild(rootPath):
    buildDirectory = "build"
    fullBuildDirectory = os.path.join(rootPath, buildDirectory)
    os.mkdir(fullBuildDirectory)
    
    # Run cmake to generate build files
    sourceDirectory = "Cpp"
    fullSourceDirectory = os.path.join(rootPath, sourceDirectory)
    subprocess.run(["cmake", "-S", fullSourceDirectory, "-B", fullBuildDirectory])
    
    # Run cmake to actually build the project
    subprocess.run(["cmake", "--build" , fullBuildDirectory])
    
def cleanCpp(rootPath):
    buildDirectory = "build"
    fullBuildDirectory = os.path.join(rootPath, buildDirectory)
    shutil.rmtree(fullBuildDirectory, ignore_errors=True)
    
if __name__ == "__main__":
    if len(sys.argv) < 2:
        sys.exit("Need to pass an action to this script: [cpp, clean-cpp, java, clean-java].")
    main(sys.argv)