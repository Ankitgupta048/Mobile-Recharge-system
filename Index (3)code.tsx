import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { supabase } from "@/integrations/supabase/client";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import {
  Smartphone,
  Zap,
  Gift,
  Shield,
  TrendingUp,
  History,
  ArrowRight,
  CheckCircle,
} from "lucide-react";
import Navbar from "@/components/Navbar";

const Index = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState<any>(null);
  const [profile, setProfile] = useState<any>(null);

  useEffect(() => {
    supabase.auth.getSession().then(({ data: { session } }) => {
      setUser(session?.user || null);
      if (session?.user) {
        fetchProfile(session.user.id);
      }
    });

    const { data: { subscription } } = supabase.auth.onAuthStateChange((_event, session) => {
      setUser(session?.user || null);
      if (session?.user) {
        fetchProfile(session.user.id);
      }
    });

    return () => subscription.unsubscribe();
  }, []);

  const fetchProfile = async (userId: string) => {
    const { data } = await supabase
      .from("profiles")
      .select("*")
      .eq("id", userId)
      .single();

    setProfile(data);
  };

  const features = [
    {
      icon: Zap,
      title: "Instant Recharge",
      description: "Quick and hassle-free mobile recharge in seconds",
    },
    {
      icon: Gift,
      title: "Cashback Rewards",
      description: "Earn up to 12% cashback on every recharge",
    },
    {
      icon: Shield,
      title: "100% Secure",
      description: "Safe and encrypted payment processing",
    },
    {
      icon: Smartphone,
      title: "All Operators",
      description: "Support for Airtel, Jio, Vi, BSNL and more",
    },
  ];

  return (
    <div className="min-h-screen bg-background">
      <Navbar />

      {/* Hero Section */}
      <section className="relative overflow-hidden bg-gradient-hero py-20 md:py-32">
        <div className="container mx-auto px-4">
          <div className="mx-auto max-w-3xl text-center">
            <Badge className="mb-6 bg-white/20 text-white border-white/30 hover:bg-white/30">
              <Gift className="w-3 h-3 mr-1" />
              Up to 12% Cashback on All Recharges
            </Badge>
            <h1 className="mb-6 text-4xl font-bold text-white md:text-6xl">
              Quick Mobile Recharge with Instant Rewards
            </h1>
            <p className="mb-8 text-lg text-white/90 md:text-xl">
              Recharge any mobile operator and earn amazing cashback. Fast, secure, and rewarding!
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              {user ? (
                <>
                  <Button
                    size="lg"
                    onClick={() => navigate("/recharge")}
                    className="bg-white text-primary hover:bg-white/90 shadow-lg h-12 px-8"
                  >
                    <Smartphone className="mr-2 h-5 w-5" />
                    Recharge Now
                  </Button>
                  <Button
                    size="lg"
                    variant="outline"
                    onClick={() => navigate("/history")}
                    className="bg-transparent border-white text-white hover:bg-white/10 h-12 px-8"
                  >
                    <History className="mr-2 h-5 w-5" />
                    View History
                  </Button>
                </>
              ) : (
                <>
                  <Button
                    size="lg"
                    onClick={() => navigate("/auth")}
                    className="bg-white text-primary hover:bg-white/90 shadow-lg h-12 px-8"
                  >
                    Get Started
                    <ArrowRight className="ml-2 h-5 w-5" />
                  </Button>
                  <Button
                    size="lg"
                    variant="outline"
                    onClick={() => navigate("/auth")}
                    className="bg-transparent border-white text-white hover:bg-white/10 h-12 px-8"
                  >
                    Sign In
                  </Button>
                </>
              )}
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-16 md:py-24">
        <div className="container mx-auto px-4">
          <div className="text-center mb-12">
            <h2 className="text-3xl font-bold mb-4 md:text-4xl">Why Choose PayRecharge?</h2>
            <p className="text-lg text-muted-foreground">
              The smartest way to recharge your mobile with maximum benefits
            </p>
          </div>

          <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-4">
            {features.map((feature, index) => {
              const Icon = feature.icon;
              return (
                <Card
                  key={index}
                  className="text-center border-2 hover:border-primary transition-all hover:shadow-glow"
                >
                  <CardHeader>
                    <div className="mx-auto mb-4 w-16 h-16 bg-gradient-primary rounded-2xl flex items-center justify-center shadow-glow">
                      <Icon className="w-8 h-8 text-white" />
                    </div>
                    <CardTitle className="text-xl">{feature.title}</CardTitle>
                  </CardHeader>
                  <CardContent>
                    <p className="text-muted-foreground">{feature.description}</p>
                  </CardContent>
                </Card>
              );
            })}
          </div>
        </div>
      </section>

      {/* Stats Section */}
      {user && profile && (
        <section className="py-16 bg-muted/30">
          <div className="container mx-auto px-4">
            <Card className="max-w-4xl mx-auto shadow-lg">
              <CardHeader>
                <CardTitle className="text-2xl">Your Dashboard</CardTitle>
              </CardHeader>
              <CardContent>
                <div className="grid gap-6 md:grid-cols-3">
                  <div className="text-center p-6 bg-gradient-card rounded-lg">
                    <TrendingUp className="w-8 h-8 mx-auto mb-2 text-primary" />
                    <p className="text-sm text-muted-foreground mb-1">Wallet Balance</p>
                    <p className="text-3xl font-bold text-primary">
                      ₹{profile.wallet_balance.toFixed(2)}
                    </p>
                  </div>
                  <div className="text-center p-6 bg-gradient-card rounded-lg">
                    <Gift className="w-8 h-8 mx-auto mb-2 text-success" />
                    <p className="text-sm text-muted-foreground mb-1">Total Cashback</p>
                    <p className="text-3xl font-bold text-success">
                      ₹{profile.total_cashback.toFixed(2)}
                    </p>
                  </div>
                  <div className="text-center p-6 bg-gradient-card rounded-lg">
                    <CheckCircle className="w-8 h-8 mx-auto mb-2 text-accent" />
                    <p className="text-sm text-muted-foreground mb-1">Status</p>
                    <p className="text-2xl font-bold text-accent">Active</p>
                  </div>
                </div>
              </CardContent>
            </Card>
          </div>
        </section>
      )}

      {/* CTA Section */}
      <section className="py-16 md:py-24">
        <div className="container mx-auto px-4">
          <Card className="max-w-3xl mx-auto bg-gradient-secondary border-0 shadow-glow">
            <CardContent className="p-8 md:p-12 text-center">
              <h2 className="text-3xl font-bold mb-4 md:text-4xl text-secondary-foreground">
                Ready to Start Earning Cashback?
              </h2>
              <p className="text-lg mb-8 text-secondary-foreground/80">
                Join thousands of users who save money on every recharge
              </p>
              <Button
                size="lg"
                onClick={() => navigate(user ? "/recharge" : "/auth")}
                className="h-12 px-8"
              >
                {user ? "Recharge Now" : "Get Started Free"}
                <ArrowRight className="ml-2 h-5 w-5" />
              </Button>
            </CardContent>
          </Card>
        </div>
      </section>
    </div>
  );
};

export default Index;
