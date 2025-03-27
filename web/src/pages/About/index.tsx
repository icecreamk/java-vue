import { useEffect } from "react";

export default function About() {

  useEffect(() => {
    console.log('about')
  }, [])
  return (
    <div>
      <h1>About</h1>
    </div>
  );
}