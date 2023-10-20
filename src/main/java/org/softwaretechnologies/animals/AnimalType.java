package org.softwaretechnologies.animals;

public enum AnimalType {
    CAT{
        @Override
        public String toString() {
            return "meow";
        }
    }, DOG{
        @Override
        public String toString() {
            return "woof";
        }
    }, COW{
        @Override
        public String toString() {
            return "moo";
        }
    };


}
